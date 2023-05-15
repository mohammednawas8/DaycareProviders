package com.loc.daycareproviders.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.loc.daycareproviders.domain.model.AccountType
import com.loc.daycareproviders.domain.model.User
import com.loc.daycareproviders.domain.repository.UserRepository
import com.loc.daycareproviders.util.Constants
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) : UserRepository {
    override suspend fun getAccountTypeForLoggedInUser(): AccountType {
        val email = auth.currentUser?.email ?: return AccountType.UNKNOWN

        return firestore
            .collection(Constants.USER_COLLECTION)
            .whereEqualTo("email", email)
            .get()
            .await()
            .toObjects(User::class.java)
            .singleOrNull()?.accountType ?: AccountType.UNKNOWN
    }

}