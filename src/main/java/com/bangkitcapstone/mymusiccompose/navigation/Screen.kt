package com.bangkitcapstone.mymusiccompose.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart")
    object Profile : Screen("profile")
    object DetailInstrument : Screen("home/{instrumentId}") {
        fun createRoute(instrumentId: Long) = "home/$instrumentId"
    }
}