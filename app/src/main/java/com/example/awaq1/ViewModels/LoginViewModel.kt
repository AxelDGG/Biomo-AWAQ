package com.example.awaq1.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.awaq1.data.formularios.local.TokenManager
import com.example.awaq1.data.formularios.remote.AuthApiService
import com.example.awaq1.data.formularios.remote.AuthRequest
import com.example.awaq1.data.formularios.remote.AuthResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authApiService: AuthApiService,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(email: String, password_user: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                // Limpiamos los datos de entrada antes de enviarlos
                val cleanEmail = email.trim()
                val cleanPassword = password_user.trim()

                // El tipo de '''response''' es Response<AuthResponse>
                val response = authApiService.signIn(AuthRequest(cleanEmail, cleanPassword))
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    // Usamos el nuevo AuthResponse para acceder a los datos de forma segura
                    if (responseBody?.token != null && responseBody.user != null) {
                        tokenManager.saveSession(
                            token = responseBody.token,
                            userId = responseBody.user.id.toString(),
                            username = responseBody.user.username,
                            email = responseBody.user.userEmail
                        )
                        _loginState.value = LoginState.Success
                    } else {
                        _loginState.value = LoginState.Error("La respuesta del servidor está incompleta.")
                    }
                } else {
                    _loginState.value = LoginState.Error("Credenciales incorrectas o error del servidor.")
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.message ?: "Error de conexión")
            }
        }
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}
