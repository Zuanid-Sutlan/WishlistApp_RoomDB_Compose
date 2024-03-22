package com.example.wishlistapp_roomdb_compose

sealed class Screen(val route: String) {
    data object HomeScreen: Screen("home_screen")
    data object AddScreen: Screen("add_screen")
//    data object DetailScreen: Screen("detail_screen")

}