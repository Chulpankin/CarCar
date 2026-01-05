package com.example.data.impl.firebase.di

import com.example.data.api.analytics.datasource.AnalyticsService
import com.example.data.impl.firebase.analytics.datasource.FirebaseAnalyticsService
import dagger.Binds
import dagger.Module

@Module
internal interface FirebaseBinderModule {

    @Binds
    fun bindFirebaseAnalyticsService(firebaseAnalyticsService: FirebaseAnalyticsService): AnalyticsService
}