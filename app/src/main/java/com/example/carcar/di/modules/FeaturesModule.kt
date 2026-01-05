package com.example.carcar.di.modules

import com.example.carcar.features.carlist.di.FeatureCarListBinderModule
import com.example.carcar.features.carsearch.di.FeatureCarSearchBinderModule
import com.example.carcar.features.favorites.di.FeatureFavoritesBinderModule
import com.example.carcar.features.signin.di.FeatureSignInBinderModule
import com.example.carcar.features.signup.di.FeatureSignUpBinderModule
import dagger.Module

@Module(includes = [
    FeatureSignInBinderModule::class,
    FeatureSignUpBinderModule::class,
    FeatureCarListBinderModule::class,
    FeatureCarSearchBinderModule::class,
    FeatureFavoritesBinderModule::class,
])
class FeaturesModule
