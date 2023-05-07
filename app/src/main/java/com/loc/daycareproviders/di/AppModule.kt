package com.loc.daycareproviders.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.loc.daycareproviders.data.repository.AuthenticationRepositoryImpl
import com.loc.daycareproviders.domain.repository.AuthenticationRepository
import com.loc.daycareproviders.domain.usecases.LoginUser
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
    fun provideAuthenticationRepository(
        firebaseAuth: FirebaseAuth,
        firebaseFirestore: FirebaseFirestore
    ): AuthenticationRepository {
        return AuthenticationRepositoryImpl(
            auth = firebaseAuth,
            firestore = firebaseFirestore
        )
    }

    @Provides
    @Singleton
    fun provideUseCases(
        authenticationRepository: AuthenticationRepository,
    ): UseCases {
        return UseCases(
            registerUser = RegisterUser(authenticationRepository),
            loginUser = LoginUser(authenticationRepository)
        )
    }

}