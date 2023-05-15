package com.loc.daycareproviders.domain.usecases

data class UseCases(
    val registerUser: RegisterUser,
    val loginUser: LoginUser,
    val getLoggedInUserAccountType: GetLoggedInUserAccountType
)