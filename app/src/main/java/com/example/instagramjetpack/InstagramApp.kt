package com.example.instagramjetpack


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

import androidx.navigation.compose.rememberNavController
import com.example.instagramjetpack.login.ui.LoginViewModel
import com.example.instagramjetpack.model.RickAndMortyActions
import com.example.instagramjetpack.search.ui.DetailViewModel
import com.example.instagramjetpack.search.ui.SearchViewModel
import com.example.instagramjetpack.ui.theme.InstagramJetPackTheme

@Composable
fun InstagramApp(
    loginViewModel: LoginViewModel,
    searchViewModel: SearchViewModel,
    detailViewModel: DetailViewModel
) {

    InstagramJetPackTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            RickAndMortyActions(navController)
        }

        InstagramNavGraph(
            navigateToSearch = navigationActions.navigateToSearch,
            navigateToDetail = navigationActions.navigateToDetail,
            navController = navController,
            loginViewModel,
            searchViewModel,
            detailViewModel

        )


    }
}