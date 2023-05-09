package com.example.instagramjetpack.login.domain

import com.example.instagramjetpack.login.data.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private  val repository: LoginRepository) {


    suspend operator fun invoke(user:String,password:String):Boolean{
        return repository.doLogin(user, password)
    }
}