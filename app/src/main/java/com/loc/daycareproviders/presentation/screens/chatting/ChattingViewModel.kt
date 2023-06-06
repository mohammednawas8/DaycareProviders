package com.loc.daycareproviders.presentation.screens.chatting

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.ktx.Firebase
import com.loc.daycareproviders.domain.model.ChattingMessage
import com.loc.daycareproviders.domain.usecases.UseCases
import com.loc.daycareproviders.util.DataState
import com.loc.daycareproviders.util.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDateTime
import java.util.Date
import java.util.LinkedList
import javax.inject.Inject

@HiltViewModel
class ChattingViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCases: UseCases,
) : ViewModel() {

    private val _state = mutableStateOf(ChattingState())
    val state: State<ChattingState> = _state

    private var conversationId: String? = null

    init {
        val username: String? = savedStateHandle["username"]
        val conversationId: String? = savedStateHandle["conversationId"]
        this.conversationId = conversationId
        if (username != null && conversationId != null) {
            _state.value = state.value.copy(
                username = username
            )
            fetchMessages(conversationId)
        }
    }

    fun changeMessage(newMessage: String) {
        _state.value = state.value.copy(
            message = newMessage
        )
    }

    private fun fetchMessages(conversationId: String) {
        useCases.fetchMessages(
            conversationId = conversationId,
            limit = 100L,
            onLoading = { isLoading ->
                _state.value = state.value.copy(
                    isLoading = isLoading
                )
            },
            onSuccess = { chattingMessages ->
                val uid = Firebase.auth.uid
                uid?.let {
                    val messages = chattingMessages.map { chattingMessage ->
                        if (uid == chattingMessage.senderUid) {
                            Message(
                                chattingMessage = chattingMessage,
                                alignment = Alignment.BottomEnd
                            )
                        } else {
                            Message(
                                chattingMessage = chattingMessage,
                                alignment = Alignment.BottomStart
                            )
                        }
                    }
                    _state.value = state.value.copy(
                        messages = messages
                    )
                }
            },
            onError = { uiComponent ->
                appendToQueue(uiComponent)
            }
        )
    }

    fun sendMessage() {
        val chattingMessage = ChattingMessage(
            text = state.value.message,
            senderUid = Firebase.auth.uid!!,
        )
        conversationId?.let {
            useCases.sendChattingMessage(
                chattingMessage = chattingMessage,
                conversationId = it
            ).onEach { dataState ->
                when (dataState) {
                    is DataState.Loading -> _state.value =
                        _state.value.copy(isMessageSending = dataState.isLoading)

                    is DataState.Success -> {
                        _state.value = _state.value.copy(
                            message = ""
                        )
                    }

                    is DataState.Response -> {
                        appendToQueue(
                            uiComponent = dataState.uiComponent
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
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