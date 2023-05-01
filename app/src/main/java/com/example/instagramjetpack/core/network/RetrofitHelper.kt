package com.example.instagramjetpack.core.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://run.mocky.io/")
            .addConverterFactory(
            GsonConverterFactory.create()
        ).build()
    }
}