package com.loc.daycareproviders.presentation.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.daycareproviders.domain.model.AccountType
import com.loc.daycareproviders.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    fun changeEmail(newEmail: String) {
        _state.value = state.value.copy(email = newEmail)
    }

    fun changePassword(newPassword: String) {
        _state.value = state.value.copy(password = newPassword)
    }

    fun changePasswordVisibility() {
        _state.value = state.value.copy(isPasswordVisible = !state.value.isPasswordVisible)
    }

    fun login(accountType: AccountType) {

    }
}