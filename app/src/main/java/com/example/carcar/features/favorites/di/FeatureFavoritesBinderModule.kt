package com.example.carcar.features.favorites.di

import com.example.carcar.features.favorites.AdapterFavoritesRouter
import com.example.favorites.api.domain.repository.FavoriteCarRepository
import com.example.favorites.api.domain.usecase.GetFavoriteCarsUseCase
import com.example.favorites.api.navigation.FavoritesRouter
import com.example.favorites.impl.data.datasource.FavoriteCarDataSource
import com.example.favorites.impl.data.repository.FavoriteCarRepositoryImpl
import com.example.favorites.api.domain.usecase.IsFavoriteUseCase
import com.example.favorites.api.domain.usecase.ToggleFavoriteUseCase
import com.example.favorites.impl.domain.usecase.GetFavoriteCarsUseCaseImpl
import com.example.favorites.impl.domain.usecase.IsFavoriteUseCaseImpl
import com.example.favorites.impl.domain.usecase.ToggleFavoriteUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface FeatureFavoritesBinderModule {

    @Binds
    fun bindAdapterFavoritesRouterToFavoritesRouter(
        adapterFavoritesRouter: AdapterFavoritesRouter
    ): FavoritesRouter

    @Binds
    fun bindFavoriteCarRepository(
        impl: FavoriteCarRepositoryImpl
    ): FavoriteCarRepository

    @Binds
    fun bindGetFavoriteCarsUseCase(
        impl: GetFavoriteCarsUseCaseImpl
    ): GetFavoriteCarsUseCase

    @Binds
    fun bindToggleFavoriteUseCase(
        impl: ToggleFavoriteUseCaseImpl
    ): ToggleFavoriteUseCase

    @Binds
    fun bindIsFavoriteUseCase(
        impl: IsFavoriteUseCaseImpl
    ): IsFavoriteUseCase
}


