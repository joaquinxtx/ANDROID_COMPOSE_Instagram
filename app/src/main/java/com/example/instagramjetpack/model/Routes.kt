package com.example.instagramjetpack.model

sealed class Routes(val route : String){
    object Login:Routes("login")
    object Home:Routes("home")
    object Profile:Routes("profile")
    object Search:Routes("search")
    object AddPublication:Routes("addPublication")
    object Reels:Routes("reels")
}
