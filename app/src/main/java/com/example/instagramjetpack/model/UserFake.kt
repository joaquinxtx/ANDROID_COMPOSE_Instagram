package com.example.instagramjetpack.model

import androidx.annotation.DrawableRes

data class UserFake(var nameUser: String,
                    var closeFriend: Boolean,
                    var historyDay: Boolean ,
                    @DrawableRes var photo: Int)
