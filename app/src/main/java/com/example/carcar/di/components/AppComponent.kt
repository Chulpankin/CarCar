package com.example.carcar.di.components

import com.example.common.di.AppScope
import com.example.carcar.CarCarApp
import com.example.carcar.presentation.MainActivity
import com.example.carcar.di.dependencies.FeatureComponentsDeps
import com.example.carcar.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component

@[AppScope Component(modules = [AppModule::class])]
interface AppComponent : FeatureComponentsDeps {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: CarCarApp): AppComponent
    }

    fun inject(application: CarCarApp)

    fun inject(mainActivity: MainActivity)

}