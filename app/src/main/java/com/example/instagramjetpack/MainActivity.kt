package com.example.instagramjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.instagramjetpack.addPublication.AddPublicationScreen

import com.example.instagramjetpack.home.HomeScreen
import com.example.instagramjetpack.home.ui.HomeViewModel
import com.example.instagramjetpack.login.ui.LoginViewModel
import com.example.instagramjetpack.model.Routes
import com.example.instagramjetpack.profile.ProfileScreen
import com.example.instagramjetpack.reels.ReelsScreen
import com.example.instagramjetpack.search.SearchScreen
import com.example.instagramjetpack.ui.theme.InstagramJetPackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramJetPackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.Login.route
                    ){
                        composable(Routes.Login.route) { LoginScreen(LoginViewModel(),navigationController) }
                        composable(Routes.Home.route) { HomeScreen(HomeViewModel(), navigationController) }
                        composable(Routes.AddPublication.route) { AddPublicationScreen( navigationController) }
                        composable(Routes.Profile.route) { ProfileScreen( navigationController) }
                        composable(Routes.Search.route) { SearchScreen( navigationController) }
                        composable(Routes.Reels.route) { ReelsScreen( navigationController) }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    InstagramJetPackTheme {
        Greeting("Android")
    }
}