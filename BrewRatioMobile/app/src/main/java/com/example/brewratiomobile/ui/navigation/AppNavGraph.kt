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
import com.example.brewratiomobile.ui.screens.WelcomeScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    val transitionDuration = 600

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {
        // 1. Layar Awal (Welcome / Onboarding Screen)
        composable(
            route = Screen.Welcome.route,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(
                        durationMillis = transitionDuration,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(durationMillis = transitionDuration))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(
                        durationMillis = transitionDuration,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(durationMillis = transitionDuration))
            }
        ) {
            WelcomeScreen(
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route)
                }
            )
        }

        // 2. Layar Sign In / Login Screen
        composable(
            route = Screen.Login.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(
                        durationMillis = transitionDuration,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(durationMillis = transitionDuration))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(
                        durationMillis = transitionDuration,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(durationMillis = transitionDuration))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(
                        durationMillis = transitionDuration,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(durationMillis = transitionDuration))
            }
        ) {
            LoginScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                    }
                }
            )
        }

        // 3. Layar Home (Setelah Berhasil Login)
        composable(
            route = Screen.Home.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(
                        durationMillis = transitionDuration,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(durationMillis = transitionDuration))
            }
        ) {
            HomeScreen()
        }
    }
}
