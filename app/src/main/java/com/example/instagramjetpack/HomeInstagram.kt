package com.example.instagramjetpack

import android.app.Activity
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*


import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape


import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instagramjetpack.model.PublisherUsers
import com.example.instagramjetpack.model.UserFake


@Composable
fun HomeInstagram() {

    Column(
        Modifier
            .fillMaxSize()
    ) {

        HeaderHome()
        UserView()
        DividerInsta()
        PublisherItem()

    }
}

//Header Logos + Iconos
@Composable
fun HeaderHome() {
    Column() {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp)
        ) {
            LogoInsta(Modifier.weight(1f))
            IconsInsta(Modifier.weight(1f))

        }


    }
}

@Composable
fun IconsInsta(modifier: Modifier) {
    Box() {
        Row(modifier = modifier, horizontalArrangement = Arrangement.End) {
            Icon(
                painterResource(id = R.drawable.ic_like),
                contentDescription = "Close App",
                modifier = Modifier
                    .size(38.dp),
                tint = Color(0xFF626464)


            )
            Box(Modifier.padding(12.dp))
            Icon(
                painterResource(id = R.drawable.ic_chat),
                contentDescription = "Close App",
                modifier = Modifier.size(38.dp),
                tint = Color(0xFF626464)

            )
        }

    }
}

@Composable
fun LogoInsta(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.insta),
        contentDescription = "logo",
        modifier = modifier.padding(8.dp),
        alignment = Alignment.TopStart
    )
}

//Divider
@Composable
fun DividerInsta() {
    Divider(
        Modifier
            .padding(8.dp)
            .height(0.7.dp)
            .fillMaxWidth(),
        color = Color(0xFF696A6B)
    )
}

//User + RecyclerView

@Composable
fun UserView() {
    val context = LocalContext.current
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(getUsersInstagram()) {
            UserItem(userFake = it) {
                Toast.makeText(context, it.nameUser, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun UserItem(userFake: UserFake, onItemSelected: (UserFake) -> Unit) {
    Card(
        modifier = Modifier.clickable { onItemSelected(userFake) }
    ) {
        Column() {
            Image(
                painter = painterResource(id = userFake.photo),
                contentDescription = "Profile image",
                modifier = Modifier
                    .padding(end = 9.dp)
                    .clip(CircleShape)
                    .size(75.dp)
            )
            Text(text = userFake.nameUser, modifier = Modifier.align(Alignment.CenterHorizontally))
        }

    }

}

fun getUsersInstagram(): List<UserFake> {
    return listOf(
        UserFake("Joaquin", false, true, R.drawable.spiderman),
        UserFake("User2", true, true, R.drawable.daredevil),
        UserFake("user3", false, true, R.drawable.flash),
        UserFake("user4", true, true, R.drawable.spiderman),
        UserFake("user5", false, false, R.drawable.wonder_woman),
        UserFake("User7", true, true, R.drawable.logan),
        UserFake("User8", false, true, R.drawable.green_lantern),
        UserFake("User9", false, false, R.drawable.batman),
    )
}

//Publisher Users + RecyclerView Vertically


@Composable
fun PublisherItem() {
    Card() {
        Column() {
            PublisherHeader()
            PublisherBody()

        }

    }
}

@Composable
fun PublisherBody() {
    Column() {
        Image(
            painter = painterResource(id = R.drawable.imagen),
            contentDescription = "Publisher User",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )
        IconsBodyPublisher()


    }
}

@Composable
fun LikesPublisher() {
    Text(text = "125 Me gusta", fontWeight = FontWeight.Bold, modifier = Modifier.padding(6.dp))
}

@Composable
fun IconsBodyPublisher() {
    Column(modifier = Modifier.padding(horizontal = 9.dp)) {
        Row() {
            Icon(
                painterResource(id = R.drawable.ic_like),
                contentDescription = "Close App",
                modifier = Modifier
                    .size(42.dp)
                    ,
                tint = Color(0xFF626464)

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
                    .size(42.dp)
                    ,
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
        LikesPublisher()
        FooterPublisher()
    }
}

@Composable
fun FooterPublisher() {

    Box() {
        Column() {
            Text(
                text = "User12345",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 5.dp)
            )
            Text(text = "hola esto es una prueba de  pantalla espero que funcione bien y que ndsaoijhbn iafdh sijofhfiauo h ")
            Text(
                text = "Ver los 1.050 comentarios",
                fontSize = 19.sp,
                fontWeight = FontWeight.Medium,
                color = Color(
                    0xFF868788
                ),
                modifier = Modifier.padding(vertical = 6.dp)
            )
            Row() {
                Text(
                    text = "Hace 2 Dias",
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

@Composable
fun PublisherHeader() {
    Box() {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            Image(
                painter = painterResource(id = R.drawable.flash),
                contentDescription = "Profile image",
                modifier = Modifier
                    .padding(end = 9.dp)
                    .clip(CircleShape)
                    .size(55.dp)
            )
            Text(
                text = "User12345",
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


fun getPublisherInstagram(): List<PublisherUsers> {
    return listOf(

        PublisherUsers(
            "userRandom",
            137,
            "lindo dia en jujuy",
            R.drawable.spiderman,
            R.drawable.spiderman
        ),
        PublisherUsers(
            "userRandom",
            137,
            "lindo dia en jujuy",
            R.drawable.spiderman,
            R.drawable.spiderman
        ),
        PublisherUsers(
            "userRandom",
            137,
            "lindo dia en jujuy",
            R.drawable.spiderman,
            R.drawable.spiderman
        ),
        PublisherUsers(
            "userRandom",
            137,
            "lindo dia en jujuy",
            R.drawable.spiderman,
            R.drawable.spiderman
        ),
        PublisherUsers(
            "userRandom",
            137,
            "lindo dia en jujuy",
            R.drawable.spiderman,
            R.drawable.spiderman
        ),
        PublisherUsers(
            "userRandom",
            137,
            "lindo dia en jujuy",
            R.drawable.spiderman,
            R.drawable.spiderman
        ),
        PublisherUsers(
            "userRandom",
            137,
            "lindo dia en jujuy",
            R.drawable.spiderman,
            R.drawable.spiderman
        ),
        PublisherUsers(
            "userRandom",
            137,
            "lindo dia en jujuy",
            R.drawable.spiderman,
            R.drawable.spiderman
        ),
        PublisherUsers(
            "userRandom",
            137,
            "lindo dia en jujuy",
            R.drawable.spiderman,
            R.drawable.spiderman
        )
    )
}

