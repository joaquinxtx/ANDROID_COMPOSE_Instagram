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
    val index by scaffoldViewModel.index.observeAsState(initial = 0)
    BottomNavigation(backgroundColor = Color.White, contentColor = Color.Black) {
        BottomNavigationItem(
            selected = index == 0,
            onClick = { scaffoldViewModel.onNavigationWindows(0, navigationController) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "home"
                )
            },
            label = { Text(text = "Home") })
        BottomNavigationItem(
            selected = index == 1 ,
            onClick = { scaffoldViewModel.onNavigationWindows(1, navigationController) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "favorite"
                )
            },
            label = { Text(text = "Fav") })
        BottomNavigationItem(
            selected = index == 2,
            onClick = { scaffoldViewModel.onNavigationWindows(2, navigationController) } ,
            icon = {
                Icon(
                    imageVector = Icons.Default.AddBox,
                    contentDescription = "person"
                )
            },
            label = { Text(text = "Person") })
        BottomNavigationItem(
            selected = index == 3,
            onClick = { scaffoldViewModel.onNavigationWindows(3, navigationController) },
            icon = {
                Icon(
                    imageVector = Icons.Default.MusicVideo,
                    contentDescription = "favorite"
                )
            },
            label = { Text(text = "Fav") })
        BottomNavigationItem(
            selected = index== 4,
            onClick = { scaffoldViewModel.onNavigationWindows(4, navigationController)  },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "person"
                )
            },
            label = { Text(text = "Person") })
    }

}