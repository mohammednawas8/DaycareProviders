package com.loc.daycareproviders.presentation.screens.chatting

import com.loc.daycareproviders.domain.model.ChattingMessage
import com.loc.daycareproviders.util.UIComponent
import java.util.LinkedList
import java.util.Queue

data class ChattingState(
    val messages: List<Message> = listOf(),
    val message: String = "",
    val username: String = "",
    val isLoading: Boolean = false,
    val isMessageSending: Boolean = false,
    val queue: Queue<UIComponent> = LinkedList()
)