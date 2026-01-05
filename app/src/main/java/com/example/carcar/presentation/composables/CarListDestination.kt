package com.example.carcar.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import com.example.carcar.navigation.viewModelWithFactory
import com.example.common.di.ComponentDepsProvider
import com.example.common.utils.Keys
import com.example.carlist.impl.di.DaggerCarListComponent
import com.example.carlist.impl.presentation.CarListViewModel
import com.example.carlist.impl.presentation.composable.CarListScreen
import com.example.carlist.impl.presentation.model.CarListEvent
import com.example.ui.themes.CarCarAppTheme

@Composable
fun CarListDestination(
    navBackStackEntry: NavBackStackEntry
) {
    val context = LocalContext.current
    val component = remember {
        DaggerCarListComponent.factory().create(ComponentDepsProvider.get(context))
    }
    val viewModel: CarListViewModel = viewModelWithFactory(navBackStackEntry, component.viewModelFactory)
    val arguments = navBackStackEntry.arguments
    LaunchedEffect(Unit) {
        viewModel.obtainEvent(
            CarListEvent.Initiate(
                make = arguments?.getString(Keys.CAR_SEARCH_MAKE_KEY)?.takeIf { it.isNotEmpty() },
                model = arguments?.getString(Keys.CAR_SEARCH_MODEL_KEY)?.takeIf { it.isNotEmpty() },
                year = arguments?.getInt(Keys.CAR_SEARCH_YEAR_KEY)?.takeIf { it > 0 },
                body = arguments?.getString(Keys.CAR_SEARCH_BODY_KEY)?.takeIf { it.isNotEmpty() },
                keyword = arguments?.getString(Keys.CAR_SEARCH_KEYWORD_KEY)?.takeIf { it.isNotEmpty() }
            )
        )
    }
    CarCarAppTheme {
        CarListScreen(viewModel)
    }
}

