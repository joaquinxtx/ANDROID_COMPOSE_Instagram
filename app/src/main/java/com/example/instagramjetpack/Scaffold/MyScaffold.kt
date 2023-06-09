package com.example.instagramjetpack

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.instagramjetpack.Scaffold.ScaffoldViewModel
import com.example.instagramjetpack.home.ui.HomeViewModel

@Composable
fun MyScaffold(content: @Composable () -> Unit , scaffoldViewModel: ScaffoldViewModel , navigationController: NavHostController) {
    Scaffold(
        bottomBar = { MyBottomNavigation(scaffoldViewModel , navigationController ) },

    ) {
        content()
    }
}

@Composable
fun MyBottomNavigation(scaffoldViewModel: ScaffoldViewModel, navigationController: NavHostController) {

    val index:Int by scaffoldViewModel.index.observeAsState( 0)

    BottomNavigation(backgroundColor = Color.White, contentColor = Color.Black) {
        BottomNavigationItem(
            onClick = { scaffoldViewModel.onNavigationWindows(index = 0, navigationController) },
            selected = true,
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "home"
                )
            },
            label = { Text(text = "Home") })
        BottomNavigationItem(
            onClick = { scaffoldViewModel.onNavigationWindows(index = 1, navigationController) },
            selected = true ,
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "favorite"
                )
            },
            label = { Text(text = "Fav") })
        BottomNavigationItem(
            onClick = { scaffoldViewModel.onNavigationWindows(index = 2, navigationController) } ,
            selected = true,
            icon = {
                Icon(
                    imageVector = Icons.Default.AddBox,
                    contentDescription = "person"
                )
            },
            label = { Text(text = "Person") })
        BottomNavigationItem(
            onClick = { scaffoldViewModel.onNavigationWindows(index = 3, navigationController) },
            selected = true,
            icon = {
                Icon(
                    imageVector = Icons.Default.MusicVideo,
                    contentDescription = "favorite"
                )
            },
            label = { Text(text = "Fav") })
        BottomNavigationItem(
            onClick = { scaffoldViewModel.onNavigationWindows(index = 4, navigationController)  },
            selected = true,
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "person"
                )
            },
            label = { Text(text = "Person") })
    }

}