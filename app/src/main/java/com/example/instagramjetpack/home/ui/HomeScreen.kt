package com.example.instagramjetpack.home


import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.instagramjetpack.R
import com.example.instagramjetpack.home.data.getPublisherInstagram
import com.example.instagramjetpack.home.data.getUsersInstagram
import com.example.instagramjetpack.home.ui.HomeViewModel
import com.example.instagramjetpack.model.PublisherUsers
import com.example.instagramjetpack.model.UserFake


@Composable
fun HomeScreen(homeViewModel: HomeViewModel, navigationController: NavHostController) {
    val listState: LazyListState = rememberLazyListState()

    Scaffold(
        topBar = { HeaderHome() },
        bottomBar = { MyBottomNavigation(homeViewModel,navigationController) }
    ) {
        Column(
            Modifier
                .fillMaxSize()

        ) {
            val showHistory by remember {
                derivedStateOf {
                    listState.firstVisibleItemIndex <= 0
                }
            }
            AnimatedVisibility(visible = showHistory) {
                UserView() // LazyRow (historias de instagram)
            }
            DividerInsta()
            UserPublisherView(state = listState)// LazyColum (Publicaciones)


        }

    }
}

//Header Logos + Iconos
@Composable
fun HeaderHome() {
    TopAppBar(title = { LogoInstagram() },
        backgroundColor = Color.White,

        actions = {
            IconButton(onClick = {}) {
                Icon(
                    painterResource(id = R.drawable.ic_like),
                    contentDescription = "Close App",
                    modifier = Modifier
                        .size(38.dp),
                    tint = Color(0xFF626464)
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    painterResource(id = R.drawable.ic_chat),
                    contentDescription = "Close App",
                    modifier = Modifier.size(38.dp),
                    tint = Color(0xFF626464)

                )
            }
        })

}

@Composable
fun MyBottomNavigation(homeViewModel: HomeViewModel, navigationController: NavHostController) {
    val index: Int by homeViewModel.index.observeAsState(initial = 0)
    BottomNavigation(backgroundColor = Color.White, contentColor = Color.Black) {
        BottomNavigationItem(
            selected = index == 0,
            onClick = { homeViewModel.onNavigationWindows(0, navigationController) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "home"
                )
            },
            label = { Text(text = "Home") })
        BottomNavigationItem(
            selected = index == 1,
            onClick = { homeViewModel.onNavigationWindows(1, navigationController) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "favorite"
                )
            },
            label = { Text(text = "Fav") })
        BottomNavigationItem(
            selected = index == 2,
            onClick = { homeViewModel.onNavigationWindows(2, navigationController) },
            icon = {
                Icon(
                    imageVector = Icons.Default.AddBox,
                    contentDescription = "person"
                )
            },
            label = { Text(text = "Person") })
        BottomNavigationItem(
            selected = index == 3,
            onClick = { homeViewModel.onNavigationWindows(3, navigationController) },
            icon = {
                Icon(
                    imageVector = Icons.Default.MusicVideo,
                    contentDescription = "favorite"
                )
            },
            label = { Text(text = "Fav") })
        BottomNavigationItem(
            selected = index == 4,
            onClick = { homeViewModel.onNavigationWindows(4, navigationController) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "person"
                )
            },
            label = { Text(text = "Person") })
    }
}


@Composable
fun LogoInstagram() {
    Image(
        painter = painterResource(id = R.drawable.insta),
        contentDescription = "logo",
        modifier = Modifier.padding(8.dp),
        alignment = Alignment.TopStart
    )
}

//Divider
@Composable
fun DividerInsta() {
    Divider(
        Modifier
            .padding(top = 8.dp)
            .height(0.7.dp)
            .fillMaxWidth(),
        color = Color(0xFF696A6B)
    )
}

//User + RecyclerView

@Composable
fun UserView() {
    val context = LocalContext.current
    Box() {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(getUsersInstagram()) {
                UserItem(userFake = it) {
                    Toast.makeText(context, it.nameUser, Toast.LENGTH_SHORT).show()
                }
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


//Publisher Users + RecyclerView Vertically

@Composable
fun UserPublisherView(state: LazyListState) {

    LazyColumn(verticalArrangement = Arrangement.spacedBy(18.dp), state = state) {
        items(getPublisherInstagram()) {
            PublisherItem(publisherUsers = it)
        }
    }
}


@Composable
fun PublisherItem(publisherUsers: PublisherUsers) {
    Card() {
        Column() {
            PublisherHeader(publisherUsers.photoProfile, publisherUsers.nameUser)
            PublisherBody(
                publisherUsers.likesPublisher,
                publisherUsers.nameUser,
                publisherUsers.footerPublisher,
                publisherUsers.date,
                publisherUsers.comments,
                publisherUsers.photoPublisher
            )

        }

    }
}

@Composable
fun PublisherBody(
    publisherLikes: Int,
    user: String,
    comment: String,
    date: String,
    numberComment: String,
    img: Int
) {
    var like by remember { mutableStateOf(false) }
    Column() {
        Image(
            painter = painterResource(id = img),
            contentDescription = "Publisher User",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )
        IconsBodyPublisher(
            unselectedIcon = {
                Icon(
                    painterResource(id = R.drawable.ic_like),
                    contentDescription = "Close App",
                    modifier = Modifier.size(38.dp),
                    tint = Color(0xFF626464)

                )
            },
            selectedIcon = {
                Icon(
                    painterResource(id = R.drawable.ic_like_filled),
                    contentDescription = "Close App",
                    modifier = Modifier.size(38.dp),
                    tint = Color(0xFFE00B0B)

                )
            },
            isSelected = like
        ) {
            like = !like
        }
        LikesPublisher(isSelected = like, publisherLikes)
        FooterPublisher(user, comment, date, numberComment)


    }
}

@Composable
fun LikesPublisher(isSelected: Boolean, publisherLikes: Int) {
    val defaultValue = 1
    Text(
        text = if (isSelected) (publisherLikes + defaultValue).toString() + " Me gustas" else publisherLikes.toString() + " Me gustas",
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(6.dp)
    )
}

@Composable
fun IconsBodyPublisher(
    unselectedIcon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit,
    isSelected: Boolean,
    onItemSelected: () -> Unit
) {

    Column(modifier = Modifier
        .padding(horizontal = 9.dp)
        .clickable { onItemSelected() }) {
        Row() {
            if (isSelected) {
                selectedIcon()
            } else {
                unselectedIcon()
            }

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
}

@Composable
fun FooterPublisher(user: String, comment: String, date: String, numberComment: String) {

    Box() {
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(
                text = user,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 5.dp)
            )
            Text(text = comment)
            Text(
                text = "Ver los $numberComment comentarios",
                fontSize = 19.sp,
                fontWeight = FontWeight.Medium,
                color = Color(
                    0xFF868788
                ),
                modifier = Modifier.padding(vertical = 6.dp)
            )
            Row() {
                Text(
                    text = "Hace $date Dias",
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
fun PublisherHeader(photo: Int, user: String) {
    Box() {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            Image(
                painter = painterResource(id = photo),
                contentDescription = "Profile image",
                modifier = Modifier
                    .padding(end = 9.dp, top = 4.dp)
                    .clip(CircleShape)
                    .size(55.dp)
            )
            Text(
                text = user,
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




