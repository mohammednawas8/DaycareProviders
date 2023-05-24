package com.loc.daycareproviders.presentation.screens.add_daycare_service

import com.loc.daycareproviders.util.UIComponent
import java.util.LinkedList
import java.util.Queue

data class AddDaycareServiceState(
    val isLoading: Boolean = false,
    val selectedImagesCount: Int = 0,
    val queue: Queue<UIComponent> = LinkedList(),
    val description: String = "",
    val price: Double? = null,
    val currency: String = ""
)