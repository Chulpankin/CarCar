package com.example.favorites.impl.di

import com.example.common.di.ComponentDeps
import com.example.common.utils.AppExceptionHandler
import com.example.data.api.analytics.datasource.AnalyticsService
import com.example.data.api.user.AuthService
import com.example.favorites.api.domain.repository.FavoriteCarRepository
import com.example.favorites.api.domain.usecase.GetFavoriteCarsUseCase
import com.example.favorites.api.navigation.FavoritesRouter

interface FavoritesDeps : ComponentDeps {

    fun favoritesRouter(): FavoritesRouter

    fun favoriteCarRepository(): FavoriteCarRepository

    fun getFavoriteCarsUseCase(): GetFavoriteCarsUseCase

    fun appExceptionHandler(): AppExceptionHandler

    fun authService(): AuthService

    fun analyticsService(): AnalyticsService
}

