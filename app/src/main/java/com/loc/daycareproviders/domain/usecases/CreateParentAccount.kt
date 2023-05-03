package com.loc.daycareproviders.domain.usecases

import com.loc.daycareproviders.domain.model.Parent
import com.loc.daycareproviders.domain.repository.AuthenticationRepository
import com.loc.daycareproviders.helper.getLoginErrorMessage
import com.loc.daycareproviders.helper.validateEmail
import com.loc.daycareproviders.util.DataState
import com.loc.daycareproviders.util.UIComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CreateParentAccount(
    private val authenticationRepository: AuthenticationRepository,
) {

    operator fun invoke(
        parent: Parent,
        email: String,
        password: String,
    ): Flow<DataState<Parent>> {
        return flow {
            try {
                emit(DataState.Loading(isLoading = true))
                val emailValidation = validateEmail(email)
                val passwordValidation = validateEmail(password)

                // When email and password are valid we register the user
                if (emailValidation.isValid && passwordValidation.isValid) {
                    val registeredParent = authenticationRepository.createParentAccount(
                        parent = parent,
                        email = email,
                        password = password
                    )
                    if (registeredParent == null) { //Unknown error
                        emit(
                            DataState.Response(
                                error = null,
                                uiComponent = UIComponent.Toast(
                                    message = "Unknown error"
                                )
                            )
                        )
                    } else {
                        emit(DataState.Success(data = registeredParent))
                    }
                }

                if (!emailValidation.isValid) {
                    emit(
                        DataState.Response(
                            error = null,
                            uiComponent = UIComponent.Toast(
                                message = emailValidation.message ?: "Unknown error"
                            )
                        )
                    )
                }

                if (!passwordValidation.isValid) {
                    emit(
                        DataState.Response(
                            error = null,
                            uiComponent = UIComponent.Toast(
                                message = passwordValidation.message ?: "Unknown error"
                            )
                        )
                    )
                }
            } catch (e: Exception) {
                val errorMessage = getLoginErrorMessage(e)
                emit(
                    DataState.Response(
                        error = e,
                        uiComponent = UIComponent.Toast(message = errorMessage)
                    )
                )
            } finally {
                emit(DataState.Loading(isLoading = false))
            }
        }
    }
}