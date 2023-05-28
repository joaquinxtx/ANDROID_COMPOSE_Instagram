package com.example.instagramjetpack.login.data.di

import com.example.instagramjetpack.login.data.AuthRepository
import com.example.instagramjetpack.login.data.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
class LoginModule {

    @Provides
    fun providesFirebaseAuth():FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providesAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

}