package com.example.carlist.impl.di

import com.example.common.di.ComponentDeps
import com.example.common.utils.AppExceptionHandler
import com.example.carlist.api.domain.repository.CarRepository
import com.example.carlist.api.domain.usecase.GetCarsUseCase
import com.example.carlist.api.navigation.CarListRouter
import com.example.data.api.analytics.datasource.AnalyticsService
import com.example.data.api.user.AuthService
import com.example.favorites.api.domain.usecase.IsFavoriteUseCase
import com.example.favorites.api.domain.usecase.ToggleFavoriteUseCase

interface CarListDeps : ComponentDeps {

    fun carListRouter(): CarListRouter

    fun carRepository(): CarRepository

    fun getCarsUseCase(): GetCarsUseCase

    fun toggleFavoriteUseCase(): ToggleFavoriteUseCase

    fun isFavoriteUseCase(): IsFavoriteUseCase

    fun authService(): AuthService

    fun appExceptionHandler(): AppExceptionHandler

    fun analyticsService(): AnalyticsService
}

