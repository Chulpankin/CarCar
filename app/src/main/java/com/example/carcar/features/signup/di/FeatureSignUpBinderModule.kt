package com.example.carcar.features.signup.di

import com.example.signup.api.domain.usecase.SignUpUseCase
import com.example.signup.api.navigation.SignUpRouter
import com.example.carcar.features.signup.AdapterSignUpRouter
import com.example.signup.impl.domain.usecase.SignUpUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface FeatureSignUpBinderModule {

    @Binds
    fun bindAdapterSignUpRouter(adapterSignUpRouter: AdapterSignUpRouter): SignUpRouter

    @Binds
    fun bindSignUpUseCaseImpl(signUpUseCaseImpl: SignUpUseCaseImpl): SignUpUseCase
}

