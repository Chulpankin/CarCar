package com.example.carcar.features.carlist.di

import com.example.carlist.api.domain.repository.CarRepository
import com.example.carlist.api.domain.usecase.GetCarsUseCase
import com.example.carlist.api.navigation.CarListRouter
import com.example.carcar.features.carlist.AdapterCarListRouter
import com.example.carlist.impl.data.repository.CarRepositoryImpl
import com.example.carlist.impl.domain.usecase.GetCarsUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface FeatureCarListBinderModule {

    @Binds
    fun bindAdapterCarListRouterToCarListRouter(adapterCarListRouter: AdapterCarListRouter): CarListRouter

    @Binds
    fun bindGetCarsUseCaseImpl(getCarsUseCaseImpl: GetCarsUseCaseImpl): GetCarsUseCase

    @Binds
    fun bindCarRepositoryImpl(carRepositoryImpl: CarRepositoryImpl): CarRepository
}

