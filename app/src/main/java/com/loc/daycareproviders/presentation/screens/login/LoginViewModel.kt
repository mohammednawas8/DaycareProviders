package com.loc.daycareproviders.presentation.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    fun changeEmail(newEmail: String) {
        _state.value = state.value.copy(email = newEmail)
    }

    fun changePassword(newPassword: String) {
        _state.value = state.value.copy(password = newPassword)
    }

    fun changePasswordVisibility(){
        _state.value = state.value.copy(isPasswordVisible = !state.value.isPasswordVisible)
    }

    fun login() {

    }

}