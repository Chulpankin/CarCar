package com.example.carcar.di.modules

import android.content.Context
import com.example.common.di.AppScope
import com.example.common.utils.AppExceptionHandler
import com.example.common.utils.ResourceManager
import com.example.data.impl.firebase.di.FirebaseModule
import com.example.data.impl.network.di.NetworkModule
import com.example.data.impl.network.di.NetworkProvidesModule
import com.example.carcar.CarCarApp
import com.example.carcar.navigation.di.NavigationModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module(includes = [
    FeatureDepsModule::class,
    FeaturesModule::class,
    NavigationModule::class,
    FirebaseModule::class,
    NetworkModule::class,
    NetworkProvidesModule::class,
])
class AppModule {
    @AppScope
    @Provides
    fun provideContext(application: CarCarApp): Context = application


    @AppScope
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @AppScope
    @Provides
    fun provideAppExceptionHandler(resourceManager: ResourceManager): AppExceptionHandler =
        AppExceptionHandler(resourceManager)
}