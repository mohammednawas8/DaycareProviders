package com.loc.daycareproviders.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import com.loc.daycareproviders.domain.model.ChattingMessage
import com.loc.daycareproviders.domain.model.Conversation
import com.loc.daycareproviders.domain.repository.ChattingRepository
import com.loc.daycareproviders.util.Constants.CONVERSATION_COLLECTION
import com.loc.daycareproviders.util.Constants.MESSAGES_COLLECTION
import com.loc.daycareproviders.util.Constants.USER_COLLECTION
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ChattingRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
) : ChattingRepository {

    override suspend fun createNewConversation(conversation: Conversation): String {
        return withContext(Dispatchers.IO) {
            val usersConversation: MutableList<Deferred<Unit>> = mutableListOf()
            val document =
                firestore.collection(CONVERSATION_COLLECTION).document()
            val conversationWithId = conversation.copy(conversationId = document.id)
            document.set(conversationWithId).await()

            val firstUserConversation = async {
                createConversationForUser(
                    userUid = conversation.firstUserUid,
                    conversation = conversationWithId
                )
            }

            val secondUserConversation = async {
                createConversationForUser(
                    userUid = conversation.secondUserUid,
                    conversation = conversationWithId
                )
            }
            usersConversation.add(firstUserConversation)
            usersConversation.add(secondUserConversation)
            usersConversation.awaitAll()
            document.id
        }

    }

    override suspend fun getConversations(limit: Long): List<Conversation> {
        return firestore.collection(USER_COLLECTION).document(auth.uid!!).collection(
            CONVERSATION_COLLECTION
        ).limit(limit).get().await().toObjects(Conversation::class.java)
    }

    override suspend fun sendMessage(chattingMessage: ChattingMessage, conversationId: String) {
        val ref = firestore.collection(CONVERSATION_COLLECTION).document(conversationId)
            .collection(MESSAGES_COLLECTION).document()
        val map = mutableMapOf<String,Any>()
        map["text"] = chattingMessage.text
        map["senderUid"] = chattingMessage.senderUid
        map["timestamp"] = FieldValue.serverTimestamp()
        ref.set(map).await()
    }

    override fun subscribeToMessages(
        conversationId: String,
        limit: Long,
        onSuccess: (List<ChattingMessage>) -> Unit,
        onError: (e: Exception) -> Unit,
    ) {
        firestore.collection(CONVERSATION_COLLECTION).document(conversationId)
            .collection(MESSAGES_COLLECTION)
            .orderBy("timestamp")
            .limit(limit)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onError(error)
                } else {
                    val messages = value?.toObjects(ChattingMessage::class.java)
                    messages?.let {
                        onSuccess(it.reversed())
                    }
                }
            }
    }

    override suspend fun getMessages(conversationId: String, limit: Long): List<ChattingMessage> {
        return firestore.collection(CONVERSATION_COLLECTION).document(conversationId).collection(
            MESSAGES_COLLECTION
        ).limit(limit).get().await().toObjects(ChattingMessage::class.java)
    }

    override suspend fun checkForConversation(conversation: Conversation): String? {
        val conversations = firestore.collection(CONVERSATION_COLLECTION)

        // [case1] when the [firstUserUid] attribute is the sender and [secondUserUid] is the receiver.
        val case1 = conversations.whereEqualTo("firstUserUid", conversation.firstUserUid)
            .whereEqualTo("secondUserUid", conversation.secondUserUid).get()
            .await().documents.singleOrNull()?.id

        // [case1] when the [firstUserUid] attribute is the receiver and [firstUserUid] is the sender.
        val case2 = conversations.whereEqualTo("firstUserUid", conversation.secondUserUid)
            .whereEqualTo("secondUserUid", conversation.firstUserUid).get()
            .await().documents.singleOrNull()?.id

        return if (case1 == null && case2 == null)
            null
        else case1 ?: case2
    }

    private suspend fun createConversationForUser(conversation: Conversation, userUid: String) {
        firestore.collection(USER_COLLECTION).document(userUid).collection(CONVERSATION_COLLECTION)
            .document().set(conversation).await()
    }
}