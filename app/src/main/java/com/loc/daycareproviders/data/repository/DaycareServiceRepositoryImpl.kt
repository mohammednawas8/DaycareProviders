package com.loc.daycareproviders.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import com.loc.daycareproviders.domain.model.DaycareService
import com.loc.daycareproviders.domain.repository.DaycareServiceRepository
import com.loc.daycareproviders.util.Constants.DAYCARE_SERVICES
import com.loc.daycareproviders.util.Constants.SERVICE_IMAGES_PATH
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.UUID

class DaycareServiceRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val storage: StorageReference,
) : DaycareServiceRepository {

    override suspend fun publishService(service: DaycareService): DaycareService {
        val userUid = auth.uid ?: throw Exception("Unknown User UID")
        val serviceWithUserUID = service.copy(userUid = userUid)
        firestore.collection(DAYCARE_SERVICES).document().set(serviceWithUserUID)
            .await()
        return serviceWithUserUID
    }

    override suspend fun uploadImages(images: List<ByteArray>): List<String> {
        return withContext(Dispatchers.IO) {
            val imagesList: MutableList<Deferred<String>> = mutableListOf()
            images.forEach { imageBytes ->
                val deferredImage = async {
                    storage.child("$SERVICE_IMAGES_PATH/${UUID.randomUUID()}")
                        .putBytes(imageBytes).await().storage.downloadUrl.await().toString()
                }
                imagesList.add(deferredImage)
            }
            imagesList.awaitAll()
        }
    }

    override suspend fun getDaycareServices(limit: Long): List<DaycareService> {
        return firestore.collection(DAYCARE_SERVICES).limit(limit).get().await().toObjects(
            DaycareService::class.java
        )
    }
}