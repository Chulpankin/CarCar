package com.example.carcar.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import com.example.carcar.navigation.viewModelWithFactory
import com.example.common.di.ComponentDepsProvider
import com.example.signin.impl.di.DaggerSignInComponent
import com.example.signin.impl.presentation.SignInViewModel
import com.example.signin.impl.presentation.composable.SignInScreen
import com.example.ui.themes.CarCarAppTheme

@Composable
fun SignInDestination(
    navBackStackEntry: NavBackStackEntry
) {
    val context = LocalContext.current
    val component = remember {
        DaggerSignInComponent.factory().create(ComponentDepsProvider.get(context))
    }
    val viewModel: SignInViewModel = viewModelWithFactory(navBackStackEntry, component.viewModelFactory)
    CarCarAppTheme {
        SignInScreen(viewModel = viewModel)
    }
}

