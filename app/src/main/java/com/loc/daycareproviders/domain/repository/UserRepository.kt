package com.loc.daycareproviders.domain.repository

import com.loc.daycareproviders.domain.model.AccountType

interface UserRepository {

    suspend fun getAccountTypeForLoggedInUser(): AccountType

}