package com.example.awaq1.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.awaq1.data.formularios.local.TokenManager
import com.example.awaq1.data.formularios.remote.AuthApiService
import com.example.awaq1.data.usuario.UsuariosRepository

class LoginViewModelFactory(
    private val authApiService: AuthApiService,
    private val tokenManager: TokenManager,
    private val usuariosRepository: UsuariosRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(authApiService, tokenManager, usuariosRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}