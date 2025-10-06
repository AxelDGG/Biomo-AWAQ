package com.example.awaq1.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.room.Transaction
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.result.Credentials
import com.example.awaq1.MainActivity
import com.example.awaq1.R
import com.example.awaq1.data.AccountInfo
import com.example.awaq1.data.usuario.UsuarioEntity
import com.example.awaq1.navigator.AppNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

// Sets global (MainActivity) field accoundInfo, from a username
@SuppressLint("NewApi")
suspend fun setAccountInfoOnLogin(context: MainActivity, username: String) {
    val usuariosRepository = context.container.usuariosRepository

    // Get user's ID in database, or create its entry if not found
    val userId: Long = usuariosRepository.getUserIdByUsername(username)
        ?: usuariosRepository.insertUser(
            UsuarioEntity(
                username = username,
                lastAccess = LocalDateTime.now().toString(),
                lastLogin = LocalDateTime.now().toString()
            )
        )
    context.accountInfo = AccountInfo(username, userId)
}

@Composable
fun PrincipalView(modifier: Modifier = Modifier, auth0: Auth0) {
    // MODIFIED: State variables
    var credentials by remember { mutableStateOf<Credentials?>(null) }
    var sessionChecked by remember { mutableStateOf(false) } // NEW: To track if we've checked for a saved session
    val context = LocalContext.current as MainActivity
    val coroutineScope = rememberCoroutineScope() // NEW: Scope for launching suspend functions from Composables

    // NEW: This effect runs once when the view is first composed to check for a saved session
    LaunchedEffect(key1 = Unit) {
        val savedData = context.sessionManager.credentialsFlow.firstOrNull()
        if (savedData != null) { // Removed isExpired() check
            val (savedCredentials, savedUsername) = savedData
            Log.d("SessionCheck", "Found valid session for user: $savedUsername")
            // A valid session was found, so we log the user in automatically
            setAccountInfoOnLogin(context, savedUsername)
            credentials = savedCredentials
        } else {
            Log.d("SessionCheck", "No valid session found.")
        }
        // Mark the session check as complete
        sessionChecked = true
    }

    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        if (!sessionChecked) {
            // NEW: Show a loading screen while we check for a session
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (credentials != null) {
            // MODIFIED: We now check if 'credentials' is not null instead of a separate 'loggedIn' boolean
            AppNavigator(
                onLogout = {
                    Log.d("AuthLogout", "Logging out!")
                    coroutineScope.launch { // NEW: Use coroutine scope
                        context.sessionManager.clearCredentials()
                    }
                    credentials = null
                },
                Modifier.padding(innerPadding))
        } else {
            LogIn(
                auth0 = auth0,
                onLoginSuccess = { newCredentials, username ->
                    coroutineScope.launch(Dispatchers.Main) {
                        // These suspend functions will run within the coroutine
                        context.sessionManager.saveCredentials(newCredentials, username)
                        setAccountInfoOnLogin(context, username)
                        credentials = newCredentials
                    }
                },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun LoginScreen(
    auth0: Auth0,
    onLoginSuccess: (Credentials, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) } // Variable de estado para el mensaje de error

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(stringResource(R.string.com_auth0_domain))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                loginWithUsernamePassword(auth0, username, password, onLoginSuccess, onError = { message ->
                    errorMessage = message // Actualiza el mensaje de error si ocurre un problema
                })
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Sesión")
        }

        // Mostrar mensaje de error si existe
        errorMessage?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Error: $it", color = Color.Red)
        }
    }
}

fun loginWithUsernamePassword(
    auth0: Auth0,
    username: String,
    password: String,
    onSuccess: (Credentials, String) -> Unit,
    onError: (String) -> Unit
) {

    val authentication = AuthenticationAPIClient(auth0)
    authentication
        .login(username, password, "Username-Password-Authentication")
        .setConnection("Username-Password-Authentication")
        .validateClaims()
        .setScope("openid profile email")
        .start(object : Callback<Credentials, AuthenticationException> {
            override fun onSuccess(result: Credentials) {
                Log.d("AuthSuccess", "Autenticado como \"${username}\" exitosamente")
                onSuccess(result, username)
            }

            override fun onFailure(error: AuthenticationException) {
                // Imprime el error completo en los logs para ver más detalles
                Log.e("AuthError", "Error de autenticación: ${error.getDescription()}")
                // error.message no es muy descriptivo, nomas dice algo como
                // "Error authenticating with server" si falla cualquier cosa.
                // onError(error.message ?: "Error desconocido")
                onError(error.getDescription())
            }
        })
}
