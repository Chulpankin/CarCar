package com.example.carcar.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavBackStackEntry
import com.example.ui.viewmodel.ViewModelProviderFactory

/**
 * Creates a ViewModel using ViewModelProviderFactory from Dagger component.
 * Uses NavBackStackEntry as ViewModelStoreOwner for proper scoping.
 */
@Composable
inline fun <reified T : ViewModel> viewModelWithFactory(
    navBackStackEntry: NavBackStackEntry,
    factory: ViewModelProviderFactory
): T {
    return remember(navBackStackEntry) {
        ViewModelProvider(navBackStackEntry, factory)[T::class.java]
    }
}

