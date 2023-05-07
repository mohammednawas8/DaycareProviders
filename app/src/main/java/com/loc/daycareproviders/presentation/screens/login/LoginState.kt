package com.loc.daycareproviders.presentation.screens.login

import com.loc.daycareproviders.util.UIComponent
import java.util.LinkedList
import java.util.Queue

data class LoginState(
    val email: String="",
    val password : String="",
    val isPasswordVisible: Boolean=false,
    val isLoading: Boolean = false,
    val queue:Queue<UIComponent> = LinkedList()
)