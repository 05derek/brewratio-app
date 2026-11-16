package com.example.brewratiomobile.ui.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("login_screen")
    data object Home : Screen("home_screen")
}