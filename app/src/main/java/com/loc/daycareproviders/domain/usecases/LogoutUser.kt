package com.loc.daycareproviders.domain.usecases

import com.loc.daycareproviders.domain.repository.AuthenticationRepository

class LogoutUser(
    private val authenticationRepository: AuthenticationRepository,
) {

    suspend operator fun invoke() {
        authenticationRepository.logout()
    }

}