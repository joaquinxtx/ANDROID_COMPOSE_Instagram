package com.example.instagramjetpack.search.ui

import android.util.Log
import android.util.Size
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import  com.example.instagramjetpack.search.domain.model.Character
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size.Companion.ORIGINAL
import com.example.instagramjetpack.Header
import retrofit2.http.Body


@Composable
fun Detail(detailViewModel: DetailViewModel ){
    val state = detailViewModel.state
   Log.d("jota",state.character.toString())
   Column(Modifier.fillMaxSize()) {
    ContentDetail(character = state.character)

   }




}
@Composable
fun CharacterImage(image: String?) {

        Box {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)

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

@Composable
fun ContentDetail(character:Character?){
    CharacterImage(image = character?.image)
}