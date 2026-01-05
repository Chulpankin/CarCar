package com.example.carcar.features.carsearch.di

import com.example.carcar.features.carsearch.AdapterCarSearchRouter
import com.example.carsearch.api.navigation.CarSearchRouter
import dagger.Binds
import dagger.Module

@Module
interface FeatureCarSearchBinderModule {

    @Binds
    fun bindAdapterCarSearchRouterToCarSearchRouter(adapterCarSearchRouter: AdapterCarSearchRouter): CarSearchRouter
}

