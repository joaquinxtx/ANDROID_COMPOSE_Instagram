package com.example.instagramjetpack.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid


import androidx.compose.foundation.shape.RoundedCornerShape


import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.instagramjetpack.MyScaffold
import com.example.instagramjetpack.Scaffold.ScaffoldViewModel
import com.example.instagramjetpack.search.domain.model.Characters
import com.example.instagramjetpack.search.ui.SearchViewModel
import kotlinx.coroutines.flow.collectLatest


@Composable
fun SearchScreen(
    onItemClicked: (Int) -> Unit,
    searchViewModel: SearchViewModel,
    navigationController: NavHostController
) {
    val state = searchViewModel.state
    val eventFlow = searchViewModel.eventFlow

//    LaunchedEffect(key1 =true ){
//        eventFlow.collectLatest { event ->
//            when(event){
//                is SearchViewModel.UIEvent.ShowSnackBar ->{
//
//                }
//            }
//
//        }
//    }

    MyScaffold(
        content = {
            Column {
                SearchTopBar(searchViewModel)
                PhotoGrid(characters = state.characters, onItemClicked = { onItemClicked(it) })

            }
        },
        scaffoldViewModel = ScaffoldViewModel(),
        navigationController = navigationController
    )
}

@Composable
fun SearchTopBar(searchViewModel: SearchViewModel) {
    TopAppBar(
        backgroundColor = Color.White,
        title = { TitleSearch(searchViewModel) },
        modifier = Modifier.height(80.dp)
    )


}

@Composable
fun TitleSearch(searchViewModel: SearchViewModel) {
    val search: String by searchViewModel.search.observeAsState("")
    val icon: ImageVector = if (search.isNotEmpty()) Icons.Default.Close else Icons.Default.Search

    Box(modifier = Modifier, contentAlignment = Alignment.Center) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 10.dp, vertical = 0.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
            textStyle = TextStyle(fontSize = 16.sp),
            value = search,
            onValueChange = { searchViewModel.onSearch(it) },
            placeholder = { Text(text = "Search", style = TextStyle(fontSize = 16.sp)) },
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color(0xFF7E7C7C),
                backgroundColor = Color(0xFFD6D5D5),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,

                ),
        )
        Icon(
            imageVector = icon,
            contentDescription = "Search Icon",
            tint = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 10.dp)
                .clickable(onClick = {
                    if (icon == Icons.Default.Close) {
                        searchViewModel.onSearch("")
                    }
                }),
        )


    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoGrid(characters: List<Characters>, onItemClicked: (Int) -> Unit) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 128.dp)
    ) {
        items(characters.size) { index ->
            CharacterItem(index = characters[index], onItemClicked = { onItemClicked(it) })

        }
    }
}

@Composable
fun CharacterItem(index: Characters, onItemClicked: (Int) -> Unit) {

    Box(modifier = Modifier.clickable {}) {

        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(index.image)
                .size(Size.ORIGINAL)
                .build()
        )

        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

    }

}
