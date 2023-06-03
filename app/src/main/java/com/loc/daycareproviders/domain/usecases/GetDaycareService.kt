package com.loc.daycareproviders.domain.usecases

import com.loc.daycareproviders.domain.model.DaycareService
import com.loc.daycareproviders.domain.repository.DaycareServiceRepository
import com.loc.daycareproviders.util.DataState
import com.loc.daycareproviders.util.UIComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GetDaycareService(
    private val daycareServiceRepository: DaycareServiceRepository,
) {

    operator fun invoke(serviceId: String): Flow<DataState<DaycareService>> {
        return flow {
            emit(DataState.Loading(isLoading = true))
            try {
                val daycareService =
                    daycareServiceRepository.getDaycareService(serviceId = serviceId)
                if (daycareService == null) {
                    emit(
                        DataState.Response(
                            error = null,
                            uiComponent = UIComponent.Text(text = "Service not found")
                        )
                    )
                } else {
                    emit(DataState.Success(daycareService))
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