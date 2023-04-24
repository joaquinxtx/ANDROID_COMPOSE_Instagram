package com.example.instagramjetpack.model

import androidx.annotation.DrawableRes

data class PublisherUsers(var nameUser: String,
                          var likesPublisher : Int,
                          var footerPublisher:String ,
                          var comments:String,
                          var date:String,
                          @DrawableRes var photoProfile: Int,
                          @DrawableRes var photoPublisher: Int)
