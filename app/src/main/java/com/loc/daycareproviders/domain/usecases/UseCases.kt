package com.loc.daycareproviders.domain.usecases

data class UseCases(
    val registerUser: RegisterUser,
    val loginUser: LoginUser,
    val getLoggedInUser: GetLoggedInUser,
    val logoutUser: LogoutUser,
    val publishDaycareService: PublishDaycareService,
    val getDaycareService: GetDaycareService,
    val createNewConversation: CreateNewConversation,
    val fetchMessages: FetchMessages,
    val sendChattingMessage: SendChattingMessage
)