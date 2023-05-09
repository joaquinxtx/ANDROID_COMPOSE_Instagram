package com.example.instagramjetpack.reels



import androidx.compose.foundation.layout.Column


import androidx.compose.runtime.Composable


import androidx.navigation.NavHostController
import com.example.instagramjetpack.MyScaffold
import com.example.instagramjetpack.Scaffold.ScaffoldViewModel
import com.example.instagramjetpack.home.HeaderHome
import com.example.instagramjetpack.home.HomeScreenComplete


@Composable
fun ReelsScreen( navigationController: NavHostController) {
    MyScaffold(
        content = { Column {
            HeaderHome()
            HomeScreenComplete()

        }},
        scaffoldViewModel = ScaffoldViewModel(),
        navigationController = navigationController
    )
}