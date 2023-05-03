package com.loc.daycareproviders.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.loc.daycareproviders.data.repository.AuthenticationRepositoryImpl
import com.loc.daycareproviders.domain.repository.AuthenticationRepository
import com.loc.daycareproviders.domain.usecases.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthenticationRepository(): AuthenticationRepository {
        return AuthenticationRepositoryImpl(
            auth = Firebase.auth,
            firestore = Firebase.firestore
        )
    }

    @Provides
    @Singleton
    fun provideUseCases(
        authenticationRepository: AuthenticationRepository,
    ): UseCases {
        return UseCases(
            createStudentAccount = CreateStudentAccount(authenticationRepository),
            createTeacherAccount = CreateTeacherAccount(authenticationRepository),
            createParentAccount = CreateParentAccount(authenticationRepository),
        )
    }

}