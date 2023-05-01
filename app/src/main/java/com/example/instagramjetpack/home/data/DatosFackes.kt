package com.example.instagramjetpack.home.data

import com.example.instagramjetpack.R
import com.example.instagramjetpack.model.PublisherUsers
import com.example.instagramjetpack.model.UserFake

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

fun getPublisherInstagram(): List<PublisherUsers> {
    return listOf(

        PublisherUsers(
            "Wonder_Woman",
            137,
            "lindo dia en jujuy",
            "123",
            "21",
            R.drawable.wonder_woman,

            R.drawable.maki
        ),
        PublisherUsers(
            "Logan_el_garras",
            1667,
            "lindo dia en jujuy",
            "123",
            "14",
            R.drawable.logan,
            R.drawable.imagen
        ),
        PublisherUsers(
            "Spidi",
            17,
            "lindo dia en jujuy",
            "1.333",
            "1",
            R.drawable.spiderman,
            R.drawable.maki
        ),
        PublisherUsers(
            "Batman_dark",
            299,
            "lindo dia en jujuy",
            "13",
            "11",
            R.drawable.batman,
            R.drawable.imagen
        ),
        PublisherUsers(
            "Spiderman_User2",
            457,
            "lindo dia en jujuy",
            "123",
            "9",
            R.drawable.spiderman,
            R.drawable.maki
        ),
        PublisherUsers(
            "Makima",
            539,
            "lindo dia en jujuy",
            "123",
            "5",
            R.drawable.maki,
            R.drawable.imagen
        ),
        PublisherUsers(
            "Flash",
            78,
            "lindo dia en jujuy",
            "123",
            "2",
            R.drawable.flash,
            R.drawable.maki
        ),
        PublisherUsers(
            "El_ciegp",
            85,
            "lindo dia en jujuy",
            "123",
            "3",
            R.drawable.daredevil,
            R.drawable.imagen
        ),
        PublisherUsers(
            "Spiderman_cuaenta3",
            37,
            "lindo dia en jujuy",
            "1.233",
            "12",
            R.drawable.spiderman,
            R.drawable.maki
        )
    )
}
