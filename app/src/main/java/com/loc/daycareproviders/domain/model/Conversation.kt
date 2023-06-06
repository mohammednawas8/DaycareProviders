package com.loc.daycareproviders.domain.model

data class Conversation(
    val firstUserUid: String ="",
    val secondUserUid: String ="",
    val receiverName: String = "",
    val conversationId: String = "",
)