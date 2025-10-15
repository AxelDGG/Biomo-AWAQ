package com.example.awaq1.view

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.awaq1.navigator.AppNavigator
import com.example.awaq1.ViewModels.LoginViewModel
import com.example.awaq1.ViewModels.LoginViewModelFactory
import com.example.awaq1.data.formularios.local.TokenManager
import com.example.awaq1.data.formularios.remote.RetroFitClient

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PrincipalView() {
    val context = LocalContext.current
    val tokenManager = TokenManager(context)
    val authToken by tokenManager.authToken.collectAsState(initial = null)

    val authApiService = RetroFitClient.create(tokenManager)
    val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory(
        authApiService,
        tokenManager
    ))

    if (authToken != null) {
        AppNavigator(onLogout = {
            loginViewModel.logout()
        })
    } else {
        LogIn(
            loginViewModel = loginViewModel,
            onLoginSuccess = { }
        )
    }
}



