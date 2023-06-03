package com.loc.daycareproviders.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DaycareService(
    val images: List<String> = emptyList(),
    val description: String = "",
    val price: Double = 0.0,
    val currency: String = "",
    val providerName: String = "",
    val serviceId: String = "",
    val userUid: String = "",
) : Parcelable
