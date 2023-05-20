package com.loc.daycareproviders.data.repository

import android.util.Log
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
    override suspend fun getLoggedInUser(): User {
        val uid = auth.currentUser?.uid ?: throw Exception("Unknown error")


        return firestore
            .collection(Constants.USER_COLLECTION)
            .document(uid)
            .get()
            .await()
            .toObject(User::class.java) ?: throw Exception("Unknown error")
    }

}