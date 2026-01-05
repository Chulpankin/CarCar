package com.example.carsearch.impl.di

import com.example.common.di.ComponentDeps
import com.example.common.utils.AppExceptionHandler
import com.example.carsearch.api.navigation.CarSearchRouter
import com.example.data.api.analytics.datasource.AnalyticsService
import com.example.data.api.car.usecase.GetCarMakesUseCase
import com.example.data.api.car.usecase.GetCarModelsUseCase
import com.example.data.api.car.usecase.GetCarTrimsUseCase
import com.example.data.api.car.usecase.GetCarYearsUseCase

interface CarSearchDeps : ComponentDeps {

    fun carSearchRouter(): CarSearchRouter

    fun getCarYearsUseCase(): GetCarYearsUseCase

    fun getCarMakesUseCase(): GetCarMakesUseCase

    fun getCarModelsUseCase(): GetCarModelsUseCase

    fun getCarTrimsUseCase(): GetCarTrimsUseCase

    fun appExceptionHandler(): AppExceptionHandler

    fun analyticsService(): AnalyticsService
}

