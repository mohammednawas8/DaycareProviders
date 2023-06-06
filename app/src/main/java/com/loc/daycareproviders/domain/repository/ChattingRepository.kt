package com.loc.daycareproviders.domain.repository

import com.loc.daycareproviders.domain.model.Conversation
import com.loc.daycareproviders.domain.model.ChattingMessage
import kotlinx.coroutines.flow.Flow

interface ChattingRepository {

    suspend fun createNewConversation(conversation: Conversation): String

    suspend fun sendMessage(chattingMessage: ChattingMessage, conversationId: String)

    fun subscribeToMessages(
        conversationId: String,
        limit: Long,
        onSuccess: (List<ChattingMessage>) -> Unit,
        onError: (e: Exception) -> Unit,
    )

    suspend fun getMessages(conversationId: String, limit: Long): List<ChattingMessage>

    suspend fun checkForConversation(conversation: Conversation): String?

    suspend fun getConversations(limit: Long): List<Conversation>
}