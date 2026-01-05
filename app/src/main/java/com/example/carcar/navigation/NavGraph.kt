package com.example.carcar.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.carcar.presentation.composables.CarListDestination
import com.example.carcar.presentation.composables.CarSearchDestination
import com.example.carcar.presentation.composables.FavoritesDestination
import com.example.carcar.presentation.composables.SignInDestination
import com.example.carcar.presentation.composables.SignUpDestination
import com.example.common.utils.Keys

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String,
    isAuthorized: Boolean,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.SignIn.route) {
            SignInDestination(it)
        }
        composable(Screen.SignUp.route) {
            SignUpDestination(it)
        }
        composable(
            route = "${Screen.CarList.route}?" +
                "${Keys.CAR_SEARCH_MAKE_KEY}={${Keys.CAR_SEARCH_MAKE_KEY}}&" +
                "${Keys.CAR_SEARCH_MODEL_KEY}={${Keys.CAR_SEARCH_MODEL_KEY}}&" +
                "${Keys.CAR_SEARCH_YEAR_KEY}={${Keys.CAR_SEARCH_YEAR_KEY}}&" +
                "${Keys.CAR_SEARCH_BODY_KEY}={${Keys.CAR_SEARCH_BODY_KEY}}&" +
                "${Keys.CAR_SEARCH_KEYWORD_KEY}={${Keys.CAR_SEARCH_KEYWORD_KEY}}",
            arguments = listOf(
                navArgument(Keys.CAR_SEARCH_MAKE_KEY) {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(Keys.CAR_SEARCH_MODEL_KEY) {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(Keys.CAR_SEARCH_YEAR_KEY) {
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument(Keys.CAR_SEARCH_BODY_KEY) {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(Keys.CAR_SEARCH_KEYWORD_KEY) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            if (isAuthorized) {
                CarListDestination(it)
            } else {
                LaunchedEffect(Unit) {
                    navController.currentDestination?.route?.takeIf {
                        it != Screen.SignIn.route && it != Screen.SignUp.route
                    }?.let {
                        navController.navigate(Screen.SignIn.route) { popUpTo(0) }
                    }
                }
            }
        }
        composable(Screen.CarSearch.route) {
            if (isAuthorized) {
                CarSearchDestination(it)
            } else {
                LaunchedEffect(Unit) {
                    navController.currentDestination?.route?.takeIf {
                        it != Screen.SignIn.route && it != Screen.SignUp.route
                    }?.let {
                        navController.navigate(Screen.SignIn.route) { popUpTo(0) }
                    }
                }
            }
        }
        composable(Screen.Favorites.route) {
            if (isAuthorized) {
                FavoritesDestination(it)
            } else {
                LaunchedEffect(Unit) {
                    navController.currentDestination?.route?.takeIf {
                        it != Screen.SignIn.route && it != Screen.SignUp.route
                    }?.let {
                        navController.navigate(Screen.SignIn.route) { popUpTo(0) }
                    }
                }
            }
        }
        composable(
            route = Screen.PostDetails.route,
            arguments = listOf(
                navArgument("postId") {
                    type = NavType.StringType
                }
            )
        ) {
            if (!isAuthorized) {
                LaunchedEffect(Unit) {
                    navController.currentDestination?.route?.takeIf {
                        it != Screen.SignIn.route && it != Screen.SignUp.route
                    }?.let {
                        navController.navigate(Screen.SignIn.route) { popUpTo(0) }
                    }
                }
            }
        }
        composable(
            route = Screen.CommentReplies.route,
            arguments = listOf(
                navArgument("parentCommentId") {
                    type = NavType.StringType
                }
            )
        ) {
            if (!isAuthorized) {
                LaunchedEffect(Unit) {
                    navController.currentDestination?.route?.takeIf {
                        it != Screen.SignIn.route && it != Screen.SignUp.route
                    }?.let {
                        navController.navigate(Screen.SignIn.route) { popUpTo(0) }
                    }
                }
            }
        }
        composable(Screen.SavePost.route) {
            if (!isAuthorized) {
                LaunchedEffect(Unit) {
                    navController.currentDestination?.route?.takeIf {
                        it != Screen.SignIn.route && it != Screen.SignUp.route
                    }?.let {
                        navController.navigate(Screen.SignIn.route) { popUpTo(0) }
                    }
                }
            }
        }
    }
}

