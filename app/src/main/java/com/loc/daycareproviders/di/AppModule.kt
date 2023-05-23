package com.loc.daycareproviders.di

import android.app.Application
import android.content.ContentResolver
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.loc.daycareproviders.data.repository.AuthenticationRepositoryImpl
import com.loc.daycareproviders.data.repository.DaycareServiceRepositoryImpl
import com.loc.daycareproviders.data.repository.UserRepositoryImpl
import com.loc.daycareproviders.domain.repository.AuthenticationRepository
import com.loc.daycareproviders.domain.repository.DaycareServiceRepository
import com.loc.daycareproviders.domain.repository.UserRepository
import com.loc.daycareproviders.domain.usecases.GetLoggedInUser
import com.loc.daycareproviders.domain.usecases.LoginUser
import com.loc.daycareproviders.domain.usecases.LogoutUser
import com.loc.daycareproviders.domain.usecases.PublishDaycareService
import com.loc.daycareproviders.domain.usecases.RegisterUser
import com.loc.daycareproviders.domain.usecases.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    @Singleton
    fun provideFirebaseFirestore() = Firebase.firestore

    @Provides
    @Singleton
    fun provideFirebaseStorage() = Firebase.storage.reference

    @Provides
    @Singleton
    fun provideAuthenticationRepository(
        firebaseAuth: FirebaseAuth,
        firebaseFirestore: FirebaseFirestore,
    ): AuthenticationRepository {
        return AuthenticationRepositoryImpl(
            auth = firebaseAuth,
            firestore = firebaseFirestore
        )
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        firebaseAuth: FirebaseAuth,
        firebaseFirestore: FirebaseFirestore,
    ): UserRepository = UserRepositoryImpl(auth = firebaseAuth, firestore = firebaseFirestore)

    @Provides
    @Singleton
    fun provideUseCases(
        authenticationRepository: AuthenticationRepository,
        userRepository: UserRepository,
        daycareServiceRepository: DaycareServiceRepository
    ): UseCases {
        return UseCases(
            registerUser = RegisterUser(authenticationRepository),
            loginUser = LoginUser(authenticationRepository),
            getLoggedInUser = GetLoggedInUser(userRepository),
            logoutUser = LogoutUser(authenticationRepository),
            publishDaycareService = PublishDaycareService(daycareServiceRepository)
        )
    }

    @Singleton
    @Provides
    fun provideDaycareServiceRepository(
        firestore: FirebaseFirestore,
        auth:FirebaseAuth,
        storage:StorageReference
    ):DaycareServiceRepository{
        return DaycareServiceRepositoryImpl(
            firestore, auth, storage
        )
    }

    @Singleton
    @Provides
    fun provideContentResolver(
        application: Application,
    ): ContentResolver = application.contentResolver

}