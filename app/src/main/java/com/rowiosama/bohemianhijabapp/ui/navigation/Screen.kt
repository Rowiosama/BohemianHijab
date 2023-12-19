package com.rowiosama.bohemianhijabapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object All : Screen("all")
    object Profile : Screen("profile")
    object Favorite : Screen("favorite")
    object DetailHijab : Screen("all/{horrorId}") {
        fun createRoute(hijabId: String) = "all/$hijabId"
    }
}