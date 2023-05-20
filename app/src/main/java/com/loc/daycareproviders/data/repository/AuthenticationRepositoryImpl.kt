package com.loc.daycareproviders.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.loc.daycareproviders.domain.model.AccountType
import com.loc.daycareproviders.domain.model.User
import com.loc.daycareproviders.domain.repository.AuthenticationRepository
import com.loc.daycareproviders.helper.WrongAccountTypeException
import com.loc.daycareproviders.util.Constants.USER_COLLECTION
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthenticationRepositoryImpl(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) : AuthenticationRepository {
    override suspend fun registerAccount(user: User, email: String, password: String): User? {
        return withContext(Dispatchers.IO) {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            if (authResult.user == null) {
                return@withContext null
            }

            firestore.collection(USER_COLLECTION).document(authResult.user!!.uid).set(user).await()
            return@withContext user
        }
    }

    override suspend fun login(accountType: AccountType,email: String, password: String): Boolean {
        val authResult = auth.signInWithEmailAndPassword(email, password).await()
        val user = firestore.collection(USER_COLLECTION).whereEqualTo("email", email).get().await()
            .toObjects(User::class.java).singleOrNull()
        Log.d("test",user.toString())
        if (authResult.user != null) {
            user?.let {
                if (user.accountType != accountType){
                    logout()
                    throw WrongAccountTypeException()
                }
                return true
            }
        }
        return false
    }

    override suspend fun logout() {
        auth.signOut()
    }
}

