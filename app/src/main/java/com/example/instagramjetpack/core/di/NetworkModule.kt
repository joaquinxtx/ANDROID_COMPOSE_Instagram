package com.example.instagramjetpack.core.di

import com.example.instagramjetpack.login.data.network.LoginClient
import com.example.instagramjetpack.search.data.source.remote.RickAndMortyApi
import com.example.instagramjetpack.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
       return Retrofit.Builder()
            .baseUrl("http://run.mocky.io/")
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()
    }
    @Singleton
    @Provides
    fun provideLoginClient(retrofit: Retrofit):LoginClient{
        return retrofit.create(LoginClient::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RemoteModules{
    @Provides
    @Singleton
    fun provideRickAndMortyApi():RickAndMortyApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RickAndMortyApi::class.java)
    }
}