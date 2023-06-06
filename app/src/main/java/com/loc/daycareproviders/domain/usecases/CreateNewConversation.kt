package com.loc.daycareproviders.domain.usecases

import android.util.Log
import com.loc.daycareproviders.domain.model.Conversation
import com.loc.daycareproviders.domain.repository.ChattingRepository
import com.loc.daycareproviders.util.DataState
import com.loc.daycareproviders.util.UIComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class CreateNewConversation(
    private val chattingRepository: ChattingRepository,
) {

    operator fun invoke(conversation: Conversation): Flow<DataState<String>> {
        return flow {
            emit(DataState.Loading(isLoading = true))
            try {
                // Check if we already have a conversation between these users.
                val conversationId =
                    chattingRepository.checkForConversation(conversation = conversation)
                if (conversationId != null) {
                    emit(DataState.Success(data = conversationId))
                } else {
                    // Create a conversation between the users
                    val newConversationId = chattingRepository.createNewConversation(conversation)
                    emit(DataState.Success(data = newConversationId))
                }

            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Response(error = e, uiComponent = UIComponent.Toast("Error :(")))
            } finally {
                emit(DataState.Loading(isLoading = false))
            }
        }
    }
}