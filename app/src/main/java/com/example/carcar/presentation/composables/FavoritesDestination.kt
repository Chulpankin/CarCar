package com.example.carcar.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import com.example.carcar.navigation.viewModelWithFactory
import com.example.common.di.ComponentDepsProvider
import com.example.favorites.impl.di.DaggerFavoritesComponent
import com.example.favorites.impl.presentation.FavoritesViewModel
import com.example.favorites.impl.presentation.composable.FavoritesScreen
import com.example.favorites.impl.presentation.model.FavoritesEvent
import com.example.ui.themes.CarCarAppTheme

@Composable
fun FavoritesDestination(
    navBackStackEntry: NavBackStackEntry
) {
    val context = LocalContext.current
    val component = remember {
        DaggerFavoritesComponent.factory().create(ComponentDepsProvider.get(context))
    }
    val viewModel: FavoritesViewModel = viewModelWithFactory(navBackStackEntry, component.viewModelFactory)
    CarCarAppTheme {
        FavoritesScreen(viewModel)
    }
}

