package com.example.instagramjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.instagramjetpack.addPublication.AddPublicationScreen

import com.example.instagramjetpack.home.HomeScreen
import com.example.instagramjetpack.login.ui.LoginViewModel
import com.example.instagramjetpack.model.RickAndMortyActions
import com.example.instagramjetpack.model.Routes
import com.example.instagramjetpack.profile.ProfileScreen
import com.example.instagramjetpack.reels.ReelsScreen
import com.example.instagramjetpack.search.SearchScreen
import com.example.instagramjetpack.search.ui.Detail
import com.example.instagramjetpack.search.ui.DetailViewModel

import com.example.instagramjetpack.search.ui.SearchViewModel
import com.example.instagramjetpack.ui.theme.InstagramJetPackTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val searchViewModel: SearchViewModel by viewModels()
    private val detailViewModel: DetailViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramApp(
                loginViewModel = loginViewModel,
                searchViewModel = searchViewModel,
                detailViewModel = detailViewModel
            )
        }
    }
}

