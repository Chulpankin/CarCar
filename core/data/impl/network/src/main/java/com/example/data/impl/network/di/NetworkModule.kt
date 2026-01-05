package com.example.data.impl.network.di

import android.content.Context
import com.example.common.di.AppScope
import com.example.data.api.car.repository.CarRepository
import com.example.data.api.car.usecase.GetCarMakesUseCase
import com.example.data.api.car.usecase.GetCarModelDetailsUseCase
import com.example.data.api.car.usecase.GetCarModelsUseCase
import com.example.data.api.car.usecase.GetCarTrimsUseCase
import com.example.data.api.car.usecase.GetCarYearsUseCase
import com.example.data.impl.network.BuildConfig
import com.example.data.impl.network.car.datasource.CarQueryDataSource
import com.example.data.impl.network.car.repository.CarRepositoryImpl
import com.example.data.impl.network.car.usecase.GetCarMakesUseCaseImpl
import com.example.data.impl.network.car.usecase.GetCarModelDetailsUseCaseImpl
import com.example.data.impl.network.car.usecase.GetCarModelsUseCaseImpl
import com.example.data.impl.network.car.usecase.GetCarTrimsUseCaseImpl
import com.example.data.impl.network.car.usecase.GetCarYearsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.Dns
import java.io.File
import java.util.concurrent.TimeUnit

@Module
interface NetworkModule {

    @Binds
    @AppScope
    fun bindCarRepository(impl: CarRepositoryImpl): CarRepository

    @Binds
    @AppScope
    fun bindGetCarYearsUseCase(impl: GetCarYearsUseCaseImpl): GetCarYearsUseCase

    @Binds
    @AppScope
    fun bindGetCarMakesUseCase(impl: GetCarMakesUseCaseImpl): GetCarMakesUseCase

    @Binds
    @AppScope
    fun bindGetCarModelsUseCase(impl: GetCarModelsUseCaseImpl): GetCarModelsUseCase

    @Binds
    @AppScope
    fun bindGetCarTrimsUseCase(impl: GetCarTrimsUseCaseImpl): GetCarTrimsUseCase

    @Binds
    @AppScope
    fun bindGetCarModelDetailsUseCase(impl: GetCarModelDetailsUseCaseImpl): GetCarModelDetailsUseCase
}

@Module
object NetworkProvidesModule {

    @Provides
    @AppScope
    fun provideHttpClient(context: Context): HttpClient = HttpClient(OkHttp) {
        engine {
            config {
                connectTimeout(BuildConfig.CONNECT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                readTimeout(BuildConfig.SOCKET_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                writeTimeout(BuildConfig.SOCKET_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                retryOnConnectionFailure(true)
                dns(Dns.SYSTEM)
                val cacheDir = File(context.cacheDir, "ktor-cache")
                cacheDir.mkdirs()
                cache(Cache(cacheDir, 10 * 1024 * 1024))
            }
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    coerceInputValues = true
                }
            )
        }
        install(Logging) {
            level = LogLevel.ALL
            logger = Logger.ANDROID
        }
    }

    @Provides
    @AppScope
    fun provideCarQueryDataSource(httpClient: HttpClient): CarQueryDataSource =
        CarQueryDataSource(httpClient, BuildConfig.BASE_URL)
}

