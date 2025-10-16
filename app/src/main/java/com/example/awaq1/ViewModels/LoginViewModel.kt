package com.example.awaq1.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.awaq1.data.formularios.local.TokenManager
import com.example.awaq1.data.formularios.remote.AuthApiService
import com.example.awaq1.data.formularios.remote.AuthRequest
import com.example.awaq1.data.formularios.remote.AuthResponse
import com.example.awaq1.data.usuario.UsuarioEntity
import com.example.awaq1.data.usuario.UsuariosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class LoginViewModel(
    private val authApiService: AuthApiService,
    private val tokenManager: TokenManager,
    private val usuariosRepository: UsuariosRepository
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
    fun logout() {
        viewModelScope.launch {
            tokenManager.clearSession()
        }
    }

    fun checkAndCreateUser() {
        viewModelScope.launch {
            val userIdStr = tokenManager.userId.firstOrNull()
            val username = tokenManager.username.firstOrNull()

            if (userIdStr != null && username != null) {
                val userId = userIdStr.toLong()

                // 2. Usamos getUserById para verificar si el usuario ya existe
                val existingUser = usuariosRepository.getUserById(userId).firstOrNull()

                // 3. Si el usuario no existe (es la primera vez que inicia sesión en este dispositivo)
                if (existingUser == null) {
                    Log.d("LoginViewModel", "El usuario con ID $userId no existe localmente. Creándolo...")

                    // Creamos la entidad del nuevo usuario
                    val newUser = UsuarioEntity(
                        username = username,
                        lastAccess = LocalDateTime.now().toString(),
                        lastLogin = LocalDateTime.now().toString()
                    ).apply {
                        id = userId
                    }

                    // 4. Usamos insertUser para guardarlo en la base de datos local
                    usuariosRepository.insertUser(newUser)
                } else {
                    Log.d("LoginViewModel", "El usuario con ID $userId ya existe localmente.")
                    // Opcional: puedes actualizar su fecha de último login si quieres
                    usuariosRepository.updateLastLogin(userId, LocalDateTime.now().toString())
                }
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
