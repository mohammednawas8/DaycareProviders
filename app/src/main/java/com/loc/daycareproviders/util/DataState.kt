package com.loc.daycareproviders.util

sealed class DataState<T> {

    data class Loading<T>(val isLoading: Boolean) : DataState<T>()

    data class Success<T>(val data: T) : DataState<T>()

    data class Response<T>(val error: Exception?, val uiComponent: UIComponent) : DataState<T>()

}