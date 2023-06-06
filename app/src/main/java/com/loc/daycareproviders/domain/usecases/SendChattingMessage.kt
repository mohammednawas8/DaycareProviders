package com.loc.daycareproviders.domain.usecases

import com.loc.daycareproviders.domain.model.ChattingMessage
import com.loc.daycareproviders.domain.repository.ChattingRepository
import com.loc.daycareproviders.util.DataState
import com.loc.daycareproviders.util.UIComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class SendChattingMessage(
    private val chattingRepository: ChattingRepository,
) {

    operator fun invoke(
        chattingMessage: ChattingMessage,
        conversationId: String,
    ): Flow<DataState<Boolean>> {
        return flow {
            emit(DataState.Loading(isLoading = true))
            try {
                chattingRepository.sendMessage(
                    chattingMessage = chattingMessage,
                    conversationId = conversationId
                )
                emit(DataState.Success(true))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(
                    DataState.Response(
                        error = e,
                        uiComponent = UIComponent.Toast("Can't send your message")
                    )
                )
            } finally {
                emit(DataState.Loading(isLoading = false))
            }
        }
    }

}