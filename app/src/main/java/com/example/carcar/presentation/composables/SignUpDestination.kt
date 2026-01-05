package com.example.carcar.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import com.example.carcar.navigation.viewModelWithFactory
import com.example.common.di.ComponentDepsProvider
import com.example.signup.impl.di.DaggerSignUpComponent
import com.example.signup.impl.presentation.SignUpViewModel
import com.example.signup.impl.presentation.composable.SignUpScreen
import com.example.ui.themes.CarCarAppTheme

@Composable
fun SignUpDestination(
    navBackStackEntry: NavBackStackEntry
) {
    val context = LocalContext.current
    val component = remember {
        DaggerSignUpComponent.factory().create(ComponentDepsProvider.get(context))
    }
    val viewModel: SignUpViewModel = viewModelWithFactory(navBackStackEntry, component.viewModelFactory)
    CarCarAppTheme {
        SignUpScreen(viewModel = viewModel)
    }
}

