package com.example.carcar

import android.app.Application
import android.util.Log
import com.example.common.di.ComponentDeps
import com.example.common.di.DepsContainer
import com.example.carcar.di.components.AppComponent
import com.example.carcar.di.components.DaggerAppComponent
import com.example.carcar.di.dependencies.ComponentDepsManager
import com.example.data.api.analytics.datasource.AnalyticsService
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.perf.FirebasePerformance
import javax.inject.Inject

class CarCarApp : Application(), DepsContainer {

    @Inject
    lateinit var depsManager: ComponentDepsManager

    @Inject
    lateinit var analytics: FirebaseAnalytics

    @Inject
    lateinit var analyticsService: AnalyticsService

    @Inject
    lateinit var crashlytics: FirebaseCrashlytics

    @Inject
    lateinit var performance: FirebasePerformance

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory()
            .create(application = this)
            .apply { inject(this@CarCarApp) }

        crashlytics.isCrashlyticsCollectionEnabled = true
        
        analytics.setAnalyticsCollectionEnabled(true)
        
        analytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, null)
        Log.d(TAG, "Firebase Analytics initialized and APP_OPEN event logged")
        
    }

    companion object {
        private const val TAG = "CarCarApp"
    }

    override fun <T : ComponentDeps> getDependencies(key: Class<T>): T {
        return depsManager.getDependencies(key)
    }
}