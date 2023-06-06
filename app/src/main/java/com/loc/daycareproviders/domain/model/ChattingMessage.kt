package com.loc.daycareproviders.domain.model

import java.util.Date


data class ChattingMessage(
    val text: String = "",
    val senderUid: String = "",
    val timestamp: Date? = null,
)
