package com.example.instagramjetpack

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.instagramjetpack.addPublication.AddPublicationScreen
import com.example.instagramjetpack.home.HomeScreen
import com.example.instagramjetpack.login.ui.LoginViewModel
import com.example.instagramjetpack.login.ui.RegisterUserScreen
import com.example.instagramjetpack.model.Routes
import com.example.instagramjetpack.profile.ProfileScreen
import com.example.instagramjetpack.reels.ReelsScreen
import com.example.instagramjetpack.search.SearchScreen
import com.example.instagramjetpack.search.ui.Detail
import com.example.instagramjetpack.search.ui.SearchViewModel


@Composable
fun InstagramNavGraph(

    navigateToSearch: () -> Unit,
    navigateToDetail: (Int) -> Unit,
    navController: NavHostController = rememberNavController(),
    loginViewModel: LoginViewModel,
    searchViewModel: SearchViewModel,



) {
    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {
        composable(Routes.Login.route) {
            LoginScreen(
                loginViewModel,
                navController
            )
        }
        composable(Routes.Home.route) { HomeScreen(navController,loginViewModel) }
        composable(Routes.AddPublication.route) {
            AddPublicationScreen(
                navController)
        }
        composable(Routes.Profile.route) { ProfileScreen(navController) }
        composable(Routes.Search.route) {
            SearchScreen(
                onItemClicked = { navigateToDetail(it) },
                searchViewModel, navController

            )
        }
        composable(
            route = Routes.SearchId.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) {
            Detail(navigationController = navController)

        }
        composable(Routes.Reels.route) { ReelsScreen(navController) }

        composable(Routes.Register.route) { RegisterUserScreen(navController , loginViewModel) }
    }

}

