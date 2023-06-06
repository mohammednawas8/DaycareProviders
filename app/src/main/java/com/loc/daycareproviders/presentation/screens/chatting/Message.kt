package com.loc.daycareproviders.presentation.screens.chatting

import androidx.compose.ui.Alignment
import com.loc.daycareproviders.domain.model.ChattingMessage

data class Message(
    val chattingMessage: ChattingMessage,
    val alignment: Alignment
)