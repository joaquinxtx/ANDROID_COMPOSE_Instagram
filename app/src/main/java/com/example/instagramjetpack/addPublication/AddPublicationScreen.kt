package com.example.instagramjetpack.addPublication



import androidx.compose.foundation.layout.Column


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavHostController
import com.example.instagramjetpack.MyScaffold
import com.example.instagramjetpack.Scaffold.ScaffoldViewModel
import com.example.instagramjetpack.home.HeaderHome
import com.example.instagramjetpack.home.HomeScreenComplete
import com.example.instagramjetpack.login.ui.LoginViewModel


@Composable
fun AddPublicationScreen( navigationController: NavHostController ,  loginViewModel: LoginViewModel = hiltViewModel()  ) {
    MyScaffold(
        content = { Column {

            HomeScreenComplete()

        }},
        scaffoldViewModel = ScaffoldViewModel(),
        navigationController = navigationController,

    )
}

