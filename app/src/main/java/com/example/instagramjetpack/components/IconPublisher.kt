package com.example.instagramjetpack.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.instagramjetpack.R
import com.example.instagramjetpack.home.FooterPublisher
import com.example.instagramjetpack.home.LikesPublisher

@Composable
fun IconsBodyPublisher(


) {
    var isClicked by remember { mutableStateOf(false) } /// pasar al view model
    val iconImage = if (isClicked) R.drawable.ic_like_filled else R.drawable.ic_like
    val iconColor = if (isClicked) Color.Red else Color(0xFF626464)


    Row() {
        Icon(
            painterResource(iconImage),
            contentDescription = "Close App",
            modifier = Modifier
                .size(38.dp)
                .clickable(onClick = {
                    isClicked = !isClicked
                }),
            tint = iconColor

        )
        Box(Modifier.padding(12.dp))
        Icon(
            painterResource(id = R.drawable.ic_chat),
            contentDescription = "Close App",
            modifier = Modifier.size(38.dp),
            tint = Color(0xFF626464)

        )
        Box(Modifier.padding(12.dp))
        Icon(
            painterResource(id = R.drawable.ic_rt),
            contentDescription = "Close App",
            modifier = Modifier
                .size(42.dp),
            tint = Color(0xFF626464)

        )
        Image(
            painterResource(id = R.drawable.ic_share),
            contentDescription = "Close App",
            modifier = Modifier
                .size(38.dp)
                .weight(1f),
            alignment = Alignment.CenterEnd,
            colorFilter = ColorFilter.tint(Color(0xFF626464))

        )

    }

}
