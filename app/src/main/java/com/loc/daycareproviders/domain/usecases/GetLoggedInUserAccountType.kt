package com.loc.daycareproviders.domain.usecases

import com.loc.daycareproviders.domain.model.AccountType
import com.loc.daycareproviders.domain.repository.UserRepository
import com.loc.daycareproviders.util.DataState
import com.loc.daycareproviders.util.UIComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GetLoggedInUserAccountType(
    private val userRepository: UserRepository,
) {

    operator fun invoke(): Flow<DataState<AccountType>> {
        return flow {
            emit(DataState.Loading(isLoading = true))
            try {
                val accountType = userRepository.getLoggedInUser().accountType
                if (accountType == AccountType.UNKNOWN) {
                    emit(
                        DataState.Response(
                            error = null,
                            uiComponent = UIComponent.Text(text = "Unknown Error")
                        )
                    )
                } else {
                    emit(
                        DataState.Success(
                            data = accountType
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