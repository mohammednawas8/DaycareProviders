package com.loc.daycareproviders.presentation.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.daycareproviders.domain.model.AccountType
import com.loc.daycareproviders.domain.usecases.UseCases
import com.loc.daycareproviders.presentation.navigation.Screen
import com.loc.daycareproviders.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    private val _navigation = MutableSharedFlow<String>()
    val navigation = _navigation.asSharedFlow()

    init {
        getLoggedInAccountType()
    }

    private fun getLoggedInAccountType() {
        useCases.getLoggedInUserAccountType().onEach { dataState ->
            when (dataState) {
                is DataState.Loading -> _state.value =
                    _state.value.copy(isLoading = dataState.isLoading)

                is DataState.Success -> {
                    val accountType = dataState.data
                    viewModelScope.launch {
                        when(accountType){
                            AccountType.STUDENT ->{
                                _navigation.emit(Screen.StudentScreen.route)
                            }
                            AccountType.TEACHER->{
                                _navigation.emit(Screen.TeacherScreen.route)
                            }
                            AccountType.PARENT ->{
                                _navigation.emit(Screen.ParentScreen.route)
                            }
                            else -> {
                                _state.value = _state.value.copy(error = "Unknown Error")
                            }
                        }
                    }

                }

                is DataState.Response -> {
                    // Here i will just show an error Text in the middel of the screen.
                    // Can be modified to handel different UI Components.
                    _state.value = _state.value.copy(error = "Unknown Error")
                }
            }
        }.launchIn(viewModelScope)
    }

}