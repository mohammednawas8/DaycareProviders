package com.loc.daycareproviders.domain.model

import java.time.LocalDateTime

data class ChattingMessage(
    val text: String = "",
    val date: LocalDateTime = LocalDateTime.now(),
    val senderUid: String = ""
)