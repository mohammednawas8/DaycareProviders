package com.loc.daycareproviders.presentation.screens.daycare_provider

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.daycareproviders.domain.usecases.UseCases
import com.loc.daycareproviders.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DaycareProviderViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val userCases: UseCases,
) : ViewModel() {

    private val _state = mutableStateOf(DaycareProviderState())
    val state: State<DaycareProviderState> = _state

    private val _navigation = MutableSharedFlow<String>()
    val navigation = _navigation.asSharedFlow()

    init {
        val username = savedStateHandle["name"] ?: ""
        _state.value = _state.value.copy(name = username)
    }

    fun logout() {
        viewModelScope.launch {
            userCases.logoutUser()
            _navigation.emit(Screen.SplashScreen.route)
        }
    }
}