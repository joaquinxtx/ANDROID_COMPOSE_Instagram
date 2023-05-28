package com.example.instagramjetpack.login.data

import com.example.instagramjetpack.login.data.resources.Resource
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email:String,password:String): Resource<FirebaseUser>
    suspend fun signup(name:String , email:String , password: String):Resource<FirebaseUser>
    fun logout()
}