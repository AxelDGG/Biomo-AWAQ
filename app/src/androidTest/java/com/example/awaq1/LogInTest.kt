package com.example.awaq1

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

// ¡Importante!
// Se debe de iniciar la app y aceptar los permisos antes de correr la prueba.
// No se puede cerrar o terminar la app antes de empezar la prueba o los anuncios
// de permiso bloquearan la prueba y no funcionara.

// Nota, se puede interactuar con la alerta durante la prueba para cancelar la alerta

// Descripción
// Esta prueba verifica que se despliegue el mensaje de error al meter una cuenta falsa

class LogInTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testWrongCredentialsShowsError() {
        Thread.sleep(1000)
        // Input an invalid email
        composeTestRule.onNodeWithText("Email").performTextInput("wrong")

        // Input an invalid password
        composeTestRule.onNodeWithText("Contraseña").performTextInput("wrongPassword")

        // Click the "ENTRAR" button
        composeTestRule.onNodeWithText("ENTRAR").performClick()
        Thread.sleep(1000)
        // Assert that the error message is displayed
        composeTestRule.onNodeWithText("Error: Wrong email or password.")
            .assertIsDisplayed()
        Thread.sleep(1000)
    }
}