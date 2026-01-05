package com.example.carcar.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import com.example.carcar.navigation.viewModelWithFactory
import com.example.common.di.ComponentDepsProvider
import com.example.carsearch.impl.di.DaggerCarSearchComponent
import com.example.carsearch.impl.presentation.CarSearchViewModel
import com.example.carsearch.impl.presentation.composable.CarSearchScreen
import com.example.ui.themes.CarCarAppTheme

@Composable
fun CarSearchDestination(
    navBackStackEntry: NavBackStackEntry
) {
    val context = LocalContext.current
    val component = remember {
        DaggerCarSearchComponent.factory().create(ComponentDepsProvider.get(context))
    }
    val viewModel: CarSearchViewModel = viewModelWithFactory(navBackStackEntry, component.viewModelFactory)
    CarCarAppTheme {
        CarSearchScreen(viewModel)
    }
}

