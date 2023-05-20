package com.loc.daycareproviders.domain.usecases

import com.loc.daycareproviders.domain.model.AccountType
import com.loc.daycareproviders.domain.model.User
import com.loc.daycareproviders.domain.repository.UserRepository
import com.loc.daycareproviders.util.DataState
import com.loc.daycareproviders.util.UIComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GetLoggedInUser(
    private val userRepository: UserRepository,
) {

    operator fun invoke(): Flow<DataState<User>> {
        return flow {
            emit(DataState.Loading(isLoading = true))
            try {
                val user = userRepository.getLoggedInUser()
                emit(DataState.Success(user))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Response(error = e, uiComponent = UIComponent.Text("Error :(")))
            } finally {
                emit(DataState.Loading(isLoading = false))
            }
        }

    }

}