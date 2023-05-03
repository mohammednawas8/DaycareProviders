package com.loc.daycareproviders.presentation.screens.login

data class LoginState(
    val email: String="",
    val password : String="",
    val isPasswordVisible: Boolean=false,
    val isLoading: Boolean = false
)