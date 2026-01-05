package com.example.data.impl.firebase.analytics.datasource

import android.os.Bundle
import android.util.Log
import com.example.data.api.analytics.datasource.AnalyticsService
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

internal class FirebaseAnalyticsService @Inject constructor(
    private val analytics: FirebaseAnalytics
) : AnalyticsService {

    override fun logScreenView(screenName: String, screenClass: String) {
        val bundle = Bundle().apply {
            putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
            putString(FirebaseAnalytics.Param.SCREEN_CLASS, screenClass)
        }
        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
        Log.d(TAG, "Screen view logged: screenName=$screenName, screenClass=$screenClass")
    }

    companion object {
        private const val TAG = "FirebaseAnalyticsService"
    }
}

