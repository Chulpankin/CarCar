package com.example.carcar.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.carcar.R

@Composable
fun BottomNavBar(
    navController: NavController,
    isAuthorized: Boolean,
    onNavigate: (String) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    if (currentRoute in listOf(
            Screen.SignIn.route,
            Screen.SignUp.route,
            Screen.PostDetails.route,
            Screen.CommentReplies.route,
            Screen.SavePost.route
        )
    ) return
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Search, contentDescription = null) },
            label = { Text(stringResource(R.string.car_search)) },
            selected = currentRoute == Screen.CarSearch.route ||
                    currentRoute == Screen.CarList.route,
            onClick = {
                if (isAuthorized) onNavigate(Screen.CarSearch.route)
                else navController.navigate(Screen.SignIn.route) { popUpTo(0) }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.FavoriteBorder, contentDescription = null) },
            label = { Text(stringResource(R.string.favorites)) },
            selected = currentRoute == Screen.Favorites.route,
            onClick = {
                if (isAuthorized) onNavigate(Screen.Favorites.route)
                else navController.navigate(Screen.SignIn.route) { popUpTo(0) }
            }
        )
    }
}

