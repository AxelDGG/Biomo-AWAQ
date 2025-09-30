package com.example.awaq1.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.foundation.clickable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.auth0.android.Auth0
import com.auth0.android.result.Credentials
import com.example.awaq1.R


@Composable
fun LogIn(
    auth0: Auth0,
    onLoginSuccess: (Credentials, String,Password: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var viewpassword by remember { mutableStateOf(false)}
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Box(modifier = modifier.fillMaxSize()) {
        Image(painter = painterResource(R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize())
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.bienvenido),
                fontSize = 37.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 110.dp)
            )
            Text(text = stringResource(R.string.inicia_sesion),
                fontSize = 30.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 150.dp)
            )
            //Email
            TextField (
                value = username,
                onValueChange = {username = it },
                label = { Text("Email") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
            )
            //Contraseña
            TextField (
                value = password,
                onValueChange = {password = it },
                label = { Text("Contraseña") },
                visualTransformation = if(viewpassword) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (viewpassword)
                        Icons.Filled.VisibilityOff
                    else
                        Icons.Filled.Visibility

                    Icon(
                        imageVector = image,
                        contentDescription = if (viewpassword) "Ocultar contraseña" else "Mostrar contraseña",
                        modifier = Modifier.clickable { viewpassword = !viewpassword }
                    )

                               },
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = {
                    loginWithUsernamePassword(auth0, username, password, onLoginSuccess, onError = { message ->
                        errorMessage = message // Actualiza el mensaje de error si ocurre un problema
                    })
                    defaultKeyboardAction(ImeAction.Done)
                }),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
            )
            //Olvidaste Contraseña
            Text(text = stringResource(R.string.forgot_password),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 5.dp),
                color = Color(0xFF4E7029),
                fontWeight = FontWeight.Bold,
            )
            //Entrar
            Button(onClick = {
                loginWithUsernamePassword(auth0, username, password, onSuccess = {cred, retunredUser, returnedPassword ->
                    onLoginSuccess(cred, retunredUser, returnedPassword)
                }, onError = { message ->
                    errorMessage = message // Actualiza el mensaje de error si ocurre un problema
                })
            },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 170.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4E7029), // Example: Dodger Blue
                    contentColor = Color(0xFFFFFFFF) // Example: White text
                )
            ) {
                Text(
                    text = stringResource(R.string.enter),
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
            }
            // Mostrar mensaje de error si existe
            errorMessage?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Error: $it", color = androidx.compose.ui.graphics.Color.Red)
            }
        }
    }

}