package com.loc.daycareproviders.presentation.screens.add_daycare_service

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
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
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.util.LinkedList
import javax.inject.Inject

@HiltViewModel
class AddDaycareServiceViewModel @Inject constructor(
    private val useCases: UseCases,
    private val contentResolver: ContentResolver,
) : ViewModel() {

    private val selectedImages = mutableListOf<ByteArray>()

    private val _state = mutableStateOf(AddDaycareServiceState())
    val state: State<AddDaycareServiceState> = _state

    private val _navigation = MutableSharedFlow<Unit>()
    val navigation = _navigation.asSharedFlow()

    fun takePicture(bmp: Bitmap?) {
        bmp?.let {
            val imageByteArray = getBmpByteArray(it)
            if (imageByteArray == null) {
                appendToQueue(
                    uiComponent = UIComponent.Toast(
                        message = "Something went wrong"
                    )
                )
                return
            }
            selectedImages.add(imageByteArray)
            _state.value = state.value.copy(
                selectedImagesCount = state.value.selectedImagesCount + 1
            )
        }
    }

    fun selectImages(images: List<Uri>) {
        images.forEach { imageUri ->
            try {
                contentResolver.openInputStream(imageUri).use { inputStream ->
                    val bmp = BitmapFactory.decodeStream(inputStream)
                    val imageByteArray = getBmpByteArray(bmp)
                    if (imageByteArray == null) {
                        appendToQueue(
                            uiComponent = UIComponent.Toast(
                                message = "Something went wrong"
                            )
                        )
                        return
                    }
                    selectedImages.add(imageByteArray)
                    _state.value = state.value.copy(
                        selectedImagesCount = state.value.selectedImagesCount + 1
                    )
                }

            } catch (e: Exception) {
                e.printStackTrace()
                appendToQueue(
                    uiComponent = UIComponent.Toast(
                        message = "Error while "
                    )
                )
            }
        }
    }

    private fun getBmpByteArray(bmp: Bitmap): ByteArray? {
        val byteArrayOutputStream = ByteArrayOutputStream()
        if (!bmp.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream)) {
            return null
        }
        return byteArrayOutputStream.toByteArray()
    }

    fun changeDescription(description: String) {
        _state.value = state.value.copy(
            description = description
        )
    }

    fun changePrice(price: Double?) {
        _state.value = state.value.copy(
            price = price
        )
    }

    fun changeCurrency(currency: String) {
        _state.value = state.value.copy(
            currency = currency
        )
    }

    fun publishService() {
        useCases.publishDaycareService(
            images = selectedImages,
            description = state.value.description,
            price = state.value.price ?: 0.0,
            currency = state.value.currency
        ).onEach { dataState ->
            when (dataState) {
                is DataState.Loading -> _state.value =
                    _state.value.copy(isLoading = dataState.isLoading)

                is DataState.Success -> {
                    viewModelScope.launch {
                        appendToQueue(
                            uiComponent = UIComponent.Toast(
                                message = "your service has been published"
                            )
                        )
                        _navigation.emit(Unit)
                    }
                }

                is DataState.Response -> {
                    appendToQueue(dataState.uiComponent)
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