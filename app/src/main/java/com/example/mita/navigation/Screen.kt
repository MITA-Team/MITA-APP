package com.example.mita.navigation

sealed class Screen (val route: String) {
    object Home : Screen("home")
    object Activity : Screen("activity")
    object Profile : Screen("profile")
}