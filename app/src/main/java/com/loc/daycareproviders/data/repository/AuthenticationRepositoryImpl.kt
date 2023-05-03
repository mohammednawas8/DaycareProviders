package com.loc.daycareproviders.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.loc.daycareproviders.domain.model.Parent
import com.loc.daycareproviders.domain.model.Student
import com.loc.daycareproviders.domain.model.Teacher
import com.loc.daycareproviders.domain.repository.AuthenticationRepository
import com.loc.daycareproviders.helper.getErrorMessage
import com.loc.daycareproviders.helper.validateEmail
import com.loc.daycareproviders.helper.validatePassword
import com.loc.daycareproviders.util.Constants.PARENT_COLLECTION
import com.loc.daycareproviders.util.Constants.STUDENT_COLLECTION
import com.loc.daycareproviders.util.Constants.TEACHER_COLLECTION
import com.loc.daycareproviders.util.DataState
import com.loc.daycareproviders.util.UIComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class AuthenticationRepositoryImpl(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) : AuthenticationRepository {

    override suspend fun createStudentAccount(
        student: Student,
        email: String,
        password: String,
    ): Student? {
        return withContext(Dispatchers.IO) {
            val accountCreationResult = auth.createUserWithEmailAndPassword(email, password).await()
            if (accountCreationResult.user == null) {
                return@withContext null
            }
            firestore.collection(STUDENT_COLLECTION).document().set(student).await()
            return@withContext student
        }
    }

    override suspend fun createParentAccount(
        parent: Parent,
        email: String,
        password: String,
    ): Parent? {
        return withContext(Dispatchers.IO) {
            val accountCreationResult = auth.createUserWithEmailAndPassword(email, password).await()
            if (accountCreationResult.user == null) {
                return@withContext null
            }
            firestore.collection(PARENT_COLLECTION).document().set(parent).await()
            return@withContext parent
        }
    }

    override suspend fun createTeacherAccount(
        teacher: Teacher,
        email: String,
        password: String,
    ): Teacher? {
        return withContext(Dispatchers.IO) {
            val accountCreationResult = auth.createUserWithEmailAndPassword(email, password).await()
            if (accountCreationResult.user == null) {
                return@withContext null
            }
            firestore.collection(TEACHER_COLLECTION).document().set(teacher).await()
            return@withContext teacher
        }
    }
}
