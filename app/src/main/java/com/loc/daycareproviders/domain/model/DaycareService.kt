package com.loc.daycareproviders.domain.model

data class DaycareService(
    val images: List<String> = emptyList(),
    val description: String = "",
    val price: Double = 0.0,
    val currency: String = "",
    val userUid: String = ""

)
