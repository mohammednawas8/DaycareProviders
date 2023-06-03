package com.loc.daycareproviders.presentation.screens.daycare_service_details

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.daycareproviders.domain.model.DaycareService
import com.loc.daycareproviders.domain.usecases.UseCases
import com.loc.daycareproviders.presentation.navigation.Screen
import com.loc.daycareproviders.util.DataState
import com.loc.daycareproviders.util.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.LinkedList
import javax.inject.Inject

@HiltViewModel
class DaycareServiceDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val useCases: UseCases,
) : ViewModel() {

    private val _state = mutableStateOf(DaycareServiceDetailsState())
    val state: State<DaycareServiceDetailsState> = _state

    init {
        val serviceId: String? = savedStateHandle["serviceId"]
        Log.d("test","Id ${serviceId.toString()}")
        serviceId?.let {
            getDaycareService(serviceId = serviceId)
        }
    }

    private fun getDaycareService(serviceId: String) {
        useCases.getDaycareService(serviceId = serviceId).onEach { dataState ->
            when (dataState) {
                is DataState.Loading -> _state.value =
                    _state.value.copy(isLoading = dataState.isLoading)

                is DataState.Success -> {
                    viewModelScope.launch {
                        _state.value = _state.value.copy(
                            daycareService = dataState.data
                        )
                    }
                }

                is DataState.Response -> {
                    appendToQueue(
                        uiComponent = dataState.uiComponent
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun appendToQueue(uiComponent: UIComponent) {
        val queue = state.value.queue
        queue.add(uiComponent)
        _state.value = _state.value.copy(queue = LinkedList()) //To force compose.
        _state.value = _state.value.copy(queue = queue)
    }

    fun removeUiComponent() {
        val queue = state.value.queue
        queue.remove()
        _state.value = _state.value.copy(queue = LinkedList()) //To force compose.
        _state.value = _state.value.copy(queue = queue)
    }
}