package com.loc.daycareproviders.presentation.screens.browse_daycare_services

import com.loc.daycareproviders.domain.model.DaycareService
import com.loc.daycareproviders.util.UIComponent
import java.util.LinkedList
import java.util.Queue

data class BrowseDaycareServicesState(
    val isLoading: Boolean = false,
    val services: List<DaycareService> = listOf(),
    val queue: Queue<UIComponent> = LinkedList()
)