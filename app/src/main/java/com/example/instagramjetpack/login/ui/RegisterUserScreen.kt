package com.example.instagramjetpack.login.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.instagramjetpack.ImageLogo
import com.example.instagramjetpack.login.data.resources.Resource
import com.example.instagramjetpack.model.Routes

@Composable
fun RegisterUserScreen(navController: NavHostController, loginViewModel: LoginViewModel) {
    val emailRegister: String by loginViewModel.email.observeAsState(initial = "")
    val passwordRegister: String by loginViewModel.password.observeAsState(initial = "")
    val nameRegister: String by loginViewModel.name.observeAsState(initial = "")
    val isCreateUserEnable: Boolean by loginViewModel.isCreateUserEnable.observeAsState(initial = false)

    val signupFlow = loginViewModel.signupFlow.collectAsState()



    ConstraintLayout (Modifier.padding(16.dp)) {
        val (email, password, name, logo, button , loginUser) = createRefs()

        ImageLogo(
            modifier = Modifier
                .constrainAs(logo) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom )
                    end.linkTo(parent.end)
                }
        )

        NameRegister(
            modifier = Modifier
                .constrainAs(name) {
                    top.linkTo(logo.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(email.top)
                },
            nameRegister = nameRegister,


        ){
            loginViewModel.onCreateUserChange(name=it, email = emailRegister, password = passwordRegister)
        }

        EmailRegister(
            modifier = Modifier.constrainAs(email) {
                top.linkTo(name.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(password.top)
            },
            emailRegister
        ){
            loginViewModel.onCreateUserChange(name=nameRegister,email=it, password = passwordRegister)
        }

        PasswordRegister(
            modifier = Modifier.constrainAs(password) {
                top.linkTo(email.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(button.top)
            },
            passwordRegister = passwordRegister,
            loginViewModel = loginViewModel
        ){
           loginViewModel.onCreateUserChange(name = nameRegister, email = emailRegister, password = it)

        }

        CreateUserButton(
            modifier = Modifier.constrainAs(button) {
                top.linkTo(password.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(loginUser.top)
            },
            loginViewModel = loginViewModel,
            registerEnable = isCreateUserEnable,
            navigationController = navController,
            passwordRegister = passwordRegister,
            emailRegister = emailRegister,
            nameRegister = nameRegister
        )

        LoginUser(modifier = Modifier.constrainAs(loginUser){
            top.linkTo(button.bottom)
            start.linkTo(parent.start)
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end)
        }, navigationController =navController )
   }

    signupFlow.value.let {
        when(it){
            is Resource.Failure -> {
                val context = LocalContext.current
                Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
            }
            Resource.Loading -> {
                CircularProgressIndicator()
            }
            is Resource.Success -> {
                LaunchedEffect(Unit){
                    navController.navigate(Routes.Home.route){
                        popUpTo(Routes.Home.route){inclusive = true}
                    }

                }
            }

        }
    }

}


@Composable
fun NameRegister(modifier: Modifier,nameRegister: String, onTextChange: (String) -> Unit){
    TextField(
        value = nameRegister,
        onValueChange = { onTextChange(it)},
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text(text = "Name") },
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF7E7C7C),
            backgroundColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}
@Composable
fun EmailRegister(modifier: Modifier,emailRegister: String, onTextChange: (String) -> Unit){
    TextField(
        value = emailRegister,
        onValueChange = { onTextChange(it)},
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF7E7C7C),
            backgroundColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}
@Composable
fun PasswordRegister(modifier: Modifier,loginViewModel: LoginViewModel ,passwordRegister: String,onTextChange: (String) -> Unit){
    val passwordVisibility: Boolean by loginViewModel.passwordVisibility.observeAsState(initial = false)
    TextField(
        value = passwordRegister,
        onValueChange = { onTextChange(it)},
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text(text = "Password") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val imagen = if (passwordVisibility) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }
            IconButton(onClick = { loginViewModel.onPasswordVisibility(passwordVisibility) }) {
                Icon(imageVector = imagen, contentDescription = "show password")
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF7E7C7C),
            backgroundColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun CreateUserButton(modifier: Modifier,registerEnable: Boolean,
                     loginViewModel: LoginViewModel,
                     navigationController: NavHostController,
                     emailRegister: String,
                     nameRegister: String,
                     passwordRegister: String){
    Button(
        onClick = { loginViewModel.signup(email =emailRegister,name=nameRegister, password = passwordRegister) },
        enabled = registerEnable,
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF229AF1),
            disabledBackgroundColor = Color(0xFF78C8F9),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "Create User")
    }
}

@Composable
fun LoginUser(modifier: Modifier,navigationController:NavHostController){
    Text(
        text = "Ya tiene una cuenta?",
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .padding(horizontal = 8.dp)
            .clickable {
                navigationController.navigate(
                    Routes.Login.route
                )
            },
        color = Color(0xFF4EA8E9)
    )
}