package com.example.carcar.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import javax.inject.Inject

class Navigator @Inject constructor() : GlobalRouter {

    private var navController: NavController? = null
    private var onAuthStateChanged: (() -> Unit)? = null

    fun attachNavController(navController: NavController) {
        this.navController = navController
    }

    fun setOnAuthStateChangedListener(listener: (() -> Unit)?) {
        this.onAuthStateChanged = listener
    }

    override fun popBackStack() {
        navController?.popBackStack()
    }

    override fun navigateToMain() {
        navController?.let { nc ->
            val currentRoute = nc.currentDestination?.route
            if (currentRoute == Screen.SignIn.route || currentRoute == Screen.SignUp.route) {
                onAuthStateChanged?.invoke()
                nc.navigate(Screen.CarSearch.route) {
                    popUpTo(nc.graph.startDestinationId) { inclusive = true }
                }
            } else {
                nc.navigate(Screen.CarSearch.route)
            }
        }
    }

    override fun navigateToSignUp() {
        navController?.navigate(Screen.SignUp.route)
    }

    override fun navigateToSignIn() {
        navController?.let { nc ->
            val options = nc.currentDestination?.route?.let {
                NavOptions.Builder()
                    .setPopUpTo(it, inclusive = true)
                    .build()
            }
            nc.navigate(Screen.SignIn.route, options)
        }
    }

    override fun navigateToCarSearch() {
        navController?.navigate(Screen.CarSearch.route)
    }

    override fun navigateToCarList(
        make: String?,
        model: String?,
        year: Int?,
        body: String?,
        keyword: String?
    ) {
        navController?.let { nc ->
            val route = Screen.CarList.createRoute(make, model, year, body, keyword)
            val options = if (nc.currentDestination?.route == Screen.SignIn.route) {
                NavOptions.Builder()
                    .setPopUpTo(Screen.SignIn.route, inclusive = true)
                    .build()
            } else null
            nc.navigate(route, options)
        }
    }
}
