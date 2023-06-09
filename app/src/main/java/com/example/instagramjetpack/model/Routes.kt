package com.example.instagramjetpack.model

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

sealed class Routes(val route : String){
    object Login:Routes("login")
    object Home:Routes("home")
    object Profile:Routes("profile")
    object Search:Routes("search")
    object SearchId:Routes("detail?id={id}"){
        fun detailId(id: Int): String {
            return "detail?id=$id"
        }
    }
    object AddPublication:Routes("addPublication")
    object Reels:Routes("reels")
    object Register:Routes("register")
}

class RickAndMortyActions(navController: NavController){
    val navigateToSearch: () -> Unit = {
        navController.navigate(Routes.Search.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToDetail = { id: Int ->
        navController.navigate(Routes.SearchId.detailId(id)) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
        }
    }
}
