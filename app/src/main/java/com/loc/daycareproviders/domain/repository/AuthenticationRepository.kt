package com.loc.daycareproviders.domain.repository

import com.loc.daycareproviders.domain.model.AccountType
import com.loc.daycareproviders.domain.model.User

interface AuthenticationRepository {
    suspend fun registerAccount(user: User, email: String, password: String): User?
    suspend fun login(accountType: AccountType,email: String, password: String): Boolean
    suspend fun logout()
}

