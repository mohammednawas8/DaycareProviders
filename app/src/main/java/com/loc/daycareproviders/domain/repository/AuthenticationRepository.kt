package com.loc.daycareproviders.domain.repository

import com.loc.daycareproviders.domain.model.User

interface AuthenticationRepository {
    suspend fun createAccount(user: User, email: String, password: String): User?
    suspend fun login(email: String, password: String): Boolean
    suspend fun logout()
}

