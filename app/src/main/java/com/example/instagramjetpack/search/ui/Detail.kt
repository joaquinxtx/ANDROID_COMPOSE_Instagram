package com.example.instagramjetpack.search.ui

import android.util.Log
import android.util.Size
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import  com.example.instagramjetpack.search.domain.model.Character
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import androidx.hilt.navigation.compose.hiltViewModel
import coil.request.ImageRequest
import coil.size.Size.Companion.ORIGINAL
import com.example.instagramjetpack.Header
import com.example.instagramjetpack.MyScaffold
import com.example.instagramjetpack.R
import com.example.instagramjetpack.Scaffold.ScaffoldViewModel
import com.example.instagramjetpack.components.IconsBodyPublisher
import com.example.instagramjetpack.search.PhotoGrid
import com.example.instagramjetpack.search.SearchTopBar
import retrofit2.http.Body


@Composable
fun Detail(
    detailViewModel: DetailViewModel = hiltViewModel(),
    navigationController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val state = detailViewModel.state
    MyScaffold(
        content = {
            Column {
                SearchTopBar(searchViewModel)
                PublisherDetailHeader(image = state.character?.image, name = state.character?.name)
                CharacterImage(image = state.character?.image)
                IconsBodyPublisher()
                FooterDetailPublisher(
                    name = state.character?.name,
                    id = state.character?.id,
                    species = state.character?.species
                )

            }
        },
        scaffoldViewModel = ScaffoldViewModel(),
        navigationController = navigationController
    )
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
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)

        )
    }
}

@Composable
fun PublisherDetailHeader(image: String?, name: String?) {
    Box() {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image)
                .build()
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            Image(
                painter = painter,
                contentDescription = "Profile image",
                modifier = Modifier
                    .padding(end = 9.dp, top = 4.dp)
                    .clip(CircleShape)
                    .size(55.dp)

            )
            Text(
                text = name ?: "",
                modifier = Modifier.align(Alignment.CenterVertically),
                fontWeight = FontWeight.Bold
            )
            Image(
                painterResource(id = R.drawable.ic_dots),
                contentDescription = "Close App",
                modifier = Modifier
                    .size(38.dp)
                    .weight(1f),
                alignment = Alignment.CenterEnd

            )

        }
    }
}

@Composable
fun FooterDetailPublisher(name: String?, id: Int?, species: String?) {

    Box() {
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(
                text = "123 Me gusta",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 5.dp)
            )
            Row {
                Text(
                    text = "$name ",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 5.dp)
                )
                Text(text = species ?: "")
            }
            Text(
                text = "Ver los $id comentarios",
                fontSize = 19.sp,
                fontWeight = FontWeight.Medium,
                color = Color(
                    0xFF868788
                ),
                modifier = Modifier.padding(vertical = 6.dp)
            )
            Row() {
                Text(
                    text = "Hace 23 Dias",
                    fontSize = 12.sp,
                    color = Color(0xFF4E4E4E),
                    modifier = Modifier.padding(end = 12.dp)
                )
                Text(
                    text = "Ver traduccion", fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}


