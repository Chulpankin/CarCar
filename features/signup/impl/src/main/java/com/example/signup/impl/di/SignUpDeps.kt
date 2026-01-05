package com.example.signup.impl.di

import com.example.signup.api.domain.usecase.SignUpUseCase
import com.example.data.api.user.AuthService
import com.example.data.api.analytics.datasource.AnalyticsService
import com.example.signup.api.navigation.SignUpRouter
import com.example.common.di.ComponentDeps
import com.example.common.utils.AppExceptionHandler
import com.example.common.utils.ResourceManager
import kotlinx.coroutines.CoroutineDispatcher

interface SignUpDeps : ComponentDeps {

    fun signUpRouter(): SignUpRouter

    fun authService(): AuthService

    fun signUpUseCase(): SignUpUseCase

    fun coroutineDispatcher(): CoroutineDispatcher

    fun appExceptionHandler(): AppExceptionHandler

    fun resourceManager(): ResourceManager

    fun analyticsService(): AnalyticsService
}

