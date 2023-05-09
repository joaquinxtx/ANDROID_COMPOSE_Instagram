package com.example.instagramjetpack.model

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

sealed class Routes(val route : String){
    object Login:Routes("login")
    object Home:Routes("home")
    object Profile:Routes("profile")
    object Search:Routes("search")
    object SearchId:Routes("search?id={id}"){
        fun detailId(id:Int):String{
            return "detail?id=$id"
        }
    }
    object AddPublication:Routes("addPublication")
    object Reels:Routes("reels")
}

class RickAndMortyActions(navController: NavController){
    val navigateToSearch:()->Unit = {
        navController.navigate(Routes.Search.route){
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }
            launchSingleTop=true
            restoreState=true
        }
    }
    val navigateToDetail = {id:Int ->
        navController.navigate(Routes.SearchId.detailId(id)){
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }
                launchSingleTop=true
        }
    }
}
