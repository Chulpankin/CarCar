package com.example.carcar.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.carcar.CarCarApp
import com.example.carcar.navigation.BottomNavBar
import com.example.carcar.navigation.NavGraph
import com.example.carcar.navigation.Navigator
import com.example.carcar.navigation.Screen
import com.example.data.api.user.AuthService
import com.example.ui.themes.CarCarAppTheme
import com.example.ui.view.composable.CarCarScaffold
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var authService: AuthService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        (application as CarCarApp).appComponent.inject(this)
        setContent {
            CarCarAppTheme {
                MainScreen()
            }
        }
    }

    @Composable
    private fun MainScreen() {
        val navController = rememberNavController()
        val isAuthorized = remember { mutableStateOf(authService.isUserAuthorized()) }
        val startDestination = remember(isAuthorized.value) {
            if (isAuthorized.value) Screen.CarSearch.route else Screen.SignIn.route
        }
        
        LaunchedEffect(navController) {
            navigator.attachNavController(navController)
            navigator.setOnAuthStateChangedListener {
                isAuthorized.value = authService.isUserAuthorized()
            }
        }
        
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        LaunchedEffect(navBackStackEntry?.destination?.route) {
            val currentRoute = navBackStackEntry?.destination?.route
            when (currentRoute) {
                Screen.CarSearch.route,
                Screen.Favorites.route,
                Screen.CarList.route -> {
                    val newAuthState = authService.isUserAuthorized()
                    if (newAuthState != isAuthorized.value) {
                        isAuthorized.value = newAuthState
                    }
                }
            }
        }
        
        CarCarScaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomNavBar(
                    navController = navController,
                    isAuthorized = isAuthorized.value,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        ) { paddingValues ->
            NavGraph(
                navController = navController,
                startDestination = startDestination,
                isAuthorized = isAuthorized.value,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}
