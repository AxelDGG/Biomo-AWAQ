package com.example.awaq1.view

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
    )
    )

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = if (authToken != null) "selectForm" else "login") {
        composable("login") {
            LogIn(
                loginViewModel = loginViewModel,
                onLoginSuccess = {
                    navController.navigate("selectForm") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
        composable("selectForm") {
            SelectFormularioScreen(navController)
        }
        // Agrega otras rutas de navegación aquí
    }
}
