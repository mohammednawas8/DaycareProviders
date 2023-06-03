package com.loc.daycareproviders.presentation.screens.daycare_service_details

import com.loc.daycareproviders.domain.model.DaycareService
import com.loc.daycareproviders.util.UIComponent
import java.util.LinkedList
import java.util.Queue

data class DaycareServiceDetailsState(
    val daycareService: DaycareService? = null,
    val isLoading: Boolean = false,
    val queue: Queue<UIComponent> = LinkedList()
) {
}