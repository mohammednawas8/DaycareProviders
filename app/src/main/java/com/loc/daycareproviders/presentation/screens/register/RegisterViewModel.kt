package com.loc.daycareproviders.presentation.screens.register

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.daycareproviders.domain.model.AccountType
import com.loc.daycareproviders.domain.model.User
import com.loc.daycareproviders.domain.usecases.UseCases
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


val TAG = "RegisterViewModel"

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val useCases: UseCases,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state

    //Navigate up after registering the account
    private val _navigation = MutableSharedFlow<Unit>()
    val navigation = _navigation.asSharedFlow()

    private var accountType: AccountType?

    init {
        val accountType: String? = savedStateHandle["accountType"]


        this.accountType = when (accountType) {
            "STUDENT" -> AccountType.STUDENT
            "TEACHER" -> AccountType.TEACHER
            "PARENT" -> AccountType.PARENT
            else -> AccountType.STUDENT
        }

    }

    fun updateFirstName(firstName: String) {
        _state.value = state.value.copy(firstName = firstName)
    }

    fun updateLastName(lastName: String) {
        _state.value = state.value.copy(lastName = lastName)
    }

    fun updateEmail(newEmail: String) {
        _state.value = state.value.copy(email = newEmail)
    }

    fun updatePassword(newPassword: String) {
        _state.value = state.value.copy(password = newPassword)
    }

    fun changePasswordVisibility() {
        _state.value = state.value.copy(isPasswordVisible = !state.value.isPasswordVisible)
    }

    fun registerAccount() {
        accountType?.let {
            val user = User(
                firstName = state.value.firstName,
                lastName = state.value.lastName,
                email = state.value.email,
                accountType = accountType!!
            )
            useCases.registerUser(
                user = user,
                email = state.value.email.trim(),
                password = state.value.password
            ).onEach { dataState ->
                when (dataState) {
                    is DataState.Loading -> _state.value =
                        _state.value.copy(isLoading = dataState.isLoading)

                    is DataState.Success -> {
                        viewModelScope.launch {
                            _navigation.emit(Unit)
                        }
                    }
                    is DataState.Response -> {/*TODO: Handel the error*/
                        appendToQueue(dataState.uiComponent)
                    }
                }
            }.launchIn(viewModelScope)
        }
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