package com.loc.daycareproviders.domain.usecases

import com.loc.daycareproviders.domain.model.User
import com.loc.daycareproviders.domain.repository.AuthenticationRepository
import com.loc.daycareproviders.helper.getCreateAccountErrorMessage
import com.loc.daycareproviders.helper.validateEmail
import com.loc.daycareproviders.helper.validateFirstName
import com.loc.daycareproviders.helper.validateLastName
import com.loc.daycareproviders.helper.validatePassword
import com.loc.daycareproviders.util.DataState
import com.loc.daycareproviders.util.UIComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterUser(
    private val authenticationRepository: AuthenticationRepository,
) {
    operator fun invoke(
        user: User,
        email: String,
        password: String,
    ): Flow<DataState<Unit>> {
        //1- Call login from authenticationRepo
        //2- Save the logged account type into data store preferences

        return flow {
            try {
                emit(DataState.Loading(isLoading = true))
                val emailValidation = validateEmail(email)
                val passwordValidation = validatePassword(password)
                val firstNameValidation = validateFirstName(user.firstName)
                val lastNameValidation = validateLastName(user.lastName)

                // When email and password are valid we register the user
                if (emailValidation.isValid && passwordValidation.isValid) {
                    val registeredUser = authenticationRepository.registerAccount(
                        user = user,
                        email = email,
                        password = password
                    )
                    if (registeredUser == null) { //Unknown error
                        emit(
                            DataState.Response(
                                error = null,
                                uiComponent = UIComponent.Toast(
                                    message = "Unknown error"
                                )
                            )
                        )
                    } else {
                        emit(DataState.Success(data = Unit))
                    }
                }

                if (!firstNameValidation.isValid) {
                    emit(
                        DataState.Response(
                            error = null,
                            uiComponent = UIComponent.Toast(
                                message = firstNameValidation.message ?: "Unknown error"
                            )
                        )
                    )
                }

                if (!lastNameValidation.isValid) {
                    emit(
                        DataState.Response(
                            error = null,
                            uiComponent = UIComponent.Toast(
                                message = lastNameValidation.message ?: "Unknown error"
                            )
                        )
                    )
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
                val errorMessage = getCreateAccountErrorMessage(e)
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