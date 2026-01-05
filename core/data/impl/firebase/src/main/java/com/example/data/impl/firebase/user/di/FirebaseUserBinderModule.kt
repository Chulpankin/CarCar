package com.example.data.impl.firebase.user.di

import com.example.data.api.user.AuthService
import com.example.data.impl.firebase.user.datasource.FirebaseAuthService
import dagger.Binds
import dagger.Module

@Module
internal interface FirebaseUserBinderModule {

    @Binds
    fun bindFirebaseAuthService(firebaseAuthService: FirebaseAuthService): AuthService
}

