package com.loc.daycareproviders.domain.repository

import com.loc.daycareproviders.domain.model.User

interface UserRepository {

    suspend fun getLoggedInUser(): User

}