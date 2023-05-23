package com.loc.daycareproviders.domain.usecases

import com.loc.daycareproviders.domain.model.DaycareService
import com.loc.daycareproviders.domain.repository.DaycareServiceRepository
import com.loc.daycareproviders.helper.validateCurrency
import com.loc.daycareproviders.helper.validateDescription
import com.loc.daycareproviders.helper.validateImages
import com.loc.daycareproviders.helper.validatePrice
import com.loc.daycareproviders.util.DataState
import com.loc.daycareproviders.util.UIComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class PublishDaycareService(
    private val daycareServiceRepository: DaycareServiceRepository,
) {

    operator fun invoke(
        images: List<ByteArray>,
        description: String,
        price: Double,
        currency: String,
    ): Flow<DataState<DaycareService>> {
        return flow {
            emit(DataState.Loading(isLoading = true))
            try {
                val imagesValidation = validateImages(images)
                val descriptionValidation = validateDescription(description)
                val priceValidation = validatePrice(price)
                val currencyValidation = validateCurrency(currency)
                if (imagesValidation.isValid && descriptionValidation.isValid && priceValidation.isValid && currencyValidation.isValid) {
                    val imageUrls = daycareServiceRepository.uploadImages(images = images)
                    val service = DaycareService(
                        images = imageUrls,
                        description = description,
                        price = price,
                        currency = currency,
                    )
                    val serviceWithUserUID = daycareServiceRepository.publishService(service)
                    emit(DataState.Success(serviceWithUserUID))
                }
                if (!imagesValidation.isValid) {
                    emit(
                        DataState.Response(
                            error = null,
                            uiComponent = UIComponent.Toast(
                                message = imagesValidation.message ?: "Unknown error"
                            )
                        )
                    )
                }
                if (!descriptionValidation.isValid) {
                    emit(
                        DataState.Response(
                            error = null,
                            uiComponent = UIComponent.Toast(
                                message = descriptionValidation.message ?: "Unknown error"
                            )
                        )
                    )
                }
                if (!priceValidation.isValid) {
                    emit(
                        DataState.Response(
                            error = null,
                            uiComponent = UIComponent.Toast(
                                message = priceValidation.message ?: "Unknown error"
                            )
                        )
                    )
                }
                if (!currencyValidation.isValid) {
                    emit(
                        DataState.Response(
                            error = null,
                            uiComponent = UIComponent.Toast(
                                message = currencyValidation.message ?: "Unknown error"
                            )
                        )
                    )
                }

            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Response(error = e, uiComponent = UIComponent.Text("Error :(")))
            } finally {
                emit(DataState.Loading(isLoading = false))
            }
        }
    }

}