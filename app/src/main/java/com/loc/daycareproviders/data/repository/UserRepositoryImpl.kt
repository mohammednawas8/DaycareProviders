package com.loc.daycareproviders.data.repository

import com.loc.daycareproviders.domain.model.AccountType
import com.loc.daycareproviders.domain.repository.UserRepository

class UserRepositoryImpl: UserRepository {
    override suspend fun getAccountTypeForLoggedInUser(): AccountType {
        TODO("Not yet implemented")
    }
}