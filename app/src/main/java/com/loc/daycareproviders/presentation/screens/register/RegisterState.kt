package com.loc.daycareproviders.presentation.screens.register

import com.loc.daycareproviders.util.UIComponent
import java.util.LinkedList
import java.util.Queue

data class RegisterState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isPasswordVisible: Boolean=false,
    val queue: Queue<UIComponent> = LinkedList()
    )