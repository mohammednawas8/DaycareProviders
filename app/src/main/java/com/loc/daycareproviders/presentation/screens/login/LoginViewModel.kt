package com.loc.daycareproviders.presentation.screens.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.daycareproviders.domain.usecases.UseCases
import com.loc.daycareproviders.presentation.navigation.Screen
import com.loc.daycareproviders.util.DataState
import com.loc.daycareproviders.util.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.LinkedList
import javax.inject.Inject

val TAG = "LoginViewModel"
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    //Navigate up after logging the account
    private val _navigation = MutableSharedFlow<String>()
    val navigation = _navigation.asSharedFlow()

    fun updateEmail(newEmail: String) {
        _state.value = state.value.copy(email = newEmail)
    }

    fun updatePassword(newPassword: String) {
        _state.value = state.value.copy(password = newPassword)
    }

    fun changePasswordVisibility() {
        _state.value = state.value.copy(isPasswordVisible = !state.value.isPasswordVisible)
    }

    fun login() {
        useCases.loginUser.invoke(
            email = state.value.email.trim(),
            password = state.value.password
        ).onEach { dataState ->
            when (dataState) {
                is DataState.Loading -> _state.value =
                    _state.value.copy(isLoading = dataState.isLoading)

                is DataState.Success -> {
                    viewModelScope.launch {
                        _navigation.emit(Screen.HomeScreen.route)
                    }
                }

                is DataState.Response -> {
                    appendToQueue(dataState.uiComponent)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun appendToQueue(uiComponent: UIComponent){
        val queue = state.value.queue
        queue.add(uiComponent)
        _state.value = _state.value.copy(queue = LinkedList()) //To force compose.
        _state.value = _state.value.copy(queue = queue)
    }

    fun removeUiComponent(){
        val queue = state.value.queue
        queue.remove()
        _state.value = _state.value.copy(queue = LinkedList()) //To force compose.
        _state.value = _state.value.copy(queue = queue)
    }

}