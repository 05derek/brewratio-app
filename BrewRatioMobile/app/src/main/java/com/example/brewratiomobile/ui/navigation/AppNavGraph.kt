package com.example.brewratiomobile.ui.navigation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.brewratiomobile.ui.screens.HomeScreen
import com.example.brewratiomobile.ui.screens.LoginScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    val transitionDuration = 750

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        // Rute Halaman Login
        composable(
            route = Screen.Login.route,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(
                        durationMillis = transitionDuration,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(
                    animationSpec = tween(durationMillis = transitionDuration)
                )
            }
        ) {
            LoginScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        // Rute Halaman Home
        composable(
            route = Screen.Home.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(
                        durationMillis = transitionDuration,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(
                    animationSpec = tween(durationMillis = transitionDuration)
                )
            }
        ) {
            HomeScreen()
        }
    }
}