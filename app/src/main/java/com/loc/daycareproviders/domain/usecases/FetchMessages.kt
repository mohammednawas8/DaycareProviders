package com.loc.daycareproviders.domain.usecases

import com.loc.daycareproviders.domain.model.ChattingMessage
import com.loc.daycareproviders.domain.repository.ChattingRepository
import com.loc.daycareproviders.util.UIComponent
import java.time.ZoneId
import java.util.Date

class FetchMessages(
    private val chattingRepository: ChattingRepository,
) {

    operator fun invoke(
        conversationId: String,
        limit: Long,
        onLoading: (Boolean) -> Unit,
        onSuccess: (List<ChattingMessage>) -> Unit,
        onError: (UIComponent) -> Unit,
    ) {
        onLoading(true)
        try {
            chattingRepository.subscribeToMessages(
                conversationId = conversationId,
                limit = limit,
                onSuccess = { chattingMessages ->
//                    val messagesWithTheUserTime = chattingMessages.map {
//                        val userLocalDate =
//                            it.timestamp?.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDate()
//                        val userDate = Date.from(
//                            userLocalDate?.atStartOfDay(ZoneId.systemDefault())?.toInstant()
//                        ) ?: it.timestamp
//                    }
                    onSuccess(chattingMessages)
                    onLoading(false)
                },
                onError = { e ->
                    onError(UIComponent.Toast(message = "Error :("))
                    onLoading(false)
                }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            onLoading(false)
        } finally {
            onLoading(false)
        }
    }

}
