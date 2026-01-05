package com.example.data.impl.firebase.di

import com.example.common.di.AppScope
import com.example.data.impl.firebase.user.di.FirebaseUserModule
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.perf.FirebasePerformance
import com.google.firebase.perf.ktx.performance
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        FirebaseBinderModule::class,
        FirebaseUserModule::class,
    ]
)
class FirebaseModule {

    @Provides
    @AppScope
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    @AppScope
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    @AppScope
    fun provideFirebaseCrashlytics(): FirebaseCrashlytics = Firebase.crashlytics

    @Provides
    @AppScope
    fun provideFirebaseAnalytics(): FirebaseAnalytics = Firebase.analytics

    @Provides
    @AppScope
    fun provideFirebasePerformance(): FirebasePerformance = Firebase.performance
}