package com.loc.daycareproviders.presentation.screens.register

data class RegisterState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false
)