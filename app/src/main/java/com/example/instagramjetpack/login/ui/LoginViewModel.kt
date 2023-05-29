package com.example.instagramjetpack.login.ui

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import androidx.navigation.NavHostController
import com.example.instagramjetpack.login.data.AuthRepository
import com.example.instagramjetpack.login.data.resources.Resource

import com.example.instagramjetpack.login.domain.LoginUseCase
import com.example.instagramjetpack.model.Routes
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase ,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable

    private val _isCreateUserEnable = MutableLiveData<Boolean>()
    val isCreateUserEnable: LiveData<Boolean> = _isCreateUserEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _passwordVisibility = MutableLiveData<Boolean>()
    val passwordVisibility: LiveData<Boolean> = _passwordVisibility

    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    private val _signupFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signupFlow: StateFlow<Resource<FirebaseUser>?> = _signupFlow

    val currentUser : FirebaseUser?
        get()=authRepository.currentUser


    init {
        if(authRepository.currentUser != null){
            _loginFlow.value = Resource.Success(authRepository.currentUser!!)
        }
    }

    fun login(email:String , password: String) = viewModelScope.launch {

        _loginFlow.value = Resource.Loading
        val result = authRepository.login(email, password)
        _loginFlow.value = result

    }
    fun signup(name:String ,email:String , password: String) = viewModelScope.launch {

        _signupFlow.value = Resource.Loading
        val result = authRepository.signup(name,email, password)
        _signupFlow.value = result

    }

    fun logout(){
        authRepository.logout()
        _signupFlow.value= null
        _loginFlow.value =null
    }




    fun onPasswordVisibility(passwordVisibility: Boolean) {
        _passwordVisibility.value = !passwordVisibility
    }


    fun onLoginChange(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isLoginEnable.value = enableLogin(email, password)
    }

    private fun enableLogin(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6


    private fun enableCreateUser(name:String ,email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6 && name.length > 4

    fun onCreateUserChange(name:String ,email: String, password: String) {
        _email.value = email
        _password.value = password
        _name.value = name
        _isCreateUserEnable.value = enableCreateUser(name ,email, password)
    }




}