package com.example.carcar.di.modules

import com.example.carsearch.impl.di.CarSearchDeps
import com.example.signin.impl.di.SignInDeps
import com.example.signup.impl.di.SignUpDeps
import com.example.common.di.ComponentDeps
import com.example.common.di.ComponentDepsKey
import com.example.carlist.impl.di.CarListDeps
import com.example.favorites.impl.di.FavoritesDeps
import com.example.carcar.di.components.AppComponent
import com.example.carcar.di.dependencies.DepsMap
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.Multibinds

@Module
interface FeatureDepsModule {

    @Multibinds
    fun depsMap(): DepsMap

    @[Binds IntoMap ComponentDepsKey(SignInDeps::class)]
    fun bindAuthDeps(appComponent: AppComponent): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(SignUpDeps::class)]
    fun bindSignUpDeps(appComponent: AppComponent): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(CarListDeps::class)]
    fun bindCarListDeps(appComponent: AppComponent): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(CarSearchDeps::class)]
    fun bindsCarSearchDeps(appComponent: AppComponent): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(FavoritesDeps::class)]
    fun bindFavoritesDeps(appComponent: AppComponent): ComponentDeps
}
