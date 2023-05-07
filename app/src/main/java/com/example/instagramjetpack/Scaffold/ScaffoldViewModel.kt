package com.example.instagramjetpack.Scaffold

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.instagramjetpack.model.Routes

class ScaffoldViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val index: LiveData<Int> = _index

    fun onNavigationWindows(index: Int, navigationController: NavHostController) {
        _index.value = index

        when (index) {
            0 -> {
                navigationController.navigate(Routes.Home.route)

            }
            1 -> {
                navigationController.navigate(Routes.Search.route)
            }
            2 -> {
                navigationController.navigate(Routes.AddPublication.route)
            }
            3 -> {
                navigationController.navigate(Routes.Reels.route)


            }
            4 -> {
                navigationController.navigate(Routes.Profile.route)


            }
            else -> {
                println("ERRORROO")
            }
        }


    }
}