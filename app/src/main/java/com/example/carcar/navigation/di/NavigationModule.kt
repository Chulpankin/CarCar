package com.example.carcar.navigation.di

import com.example.common.di.AppScope
import com.example.carcar.navigation.GlobalRouter
import com.example.carcar.navigation.Navigator
import dagger.Module
import dagger.Provides

@Module
class NavigationModule {

    @AppScope
    @Provides
    fun provideNavigator(): Navigator = Navigator()

    @AppScope
    @Provides
    fun provideGlobalRouter(navigator: Navigator): GlobalRouter = navigator
}