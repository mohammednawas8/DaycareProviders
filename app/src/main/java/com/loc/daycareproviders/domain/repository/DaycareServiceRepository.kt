package com.loc.daycareproviders.domain.repository

import com.loc.daycareproviders.domain.model.DaycareService

interface DaycareServiceRepository {

    suspend fun publishService(
        service: DaycareService,
    ): DaycareService

    suspend fun uploadImages(
        images: List<ByteArray>,
    ): List<String>

    suspend fun getDaycareServices(limit: Long): List<DaycareService>

    suspend fun getDaycareService(serviceId: String): DaycareService?

}