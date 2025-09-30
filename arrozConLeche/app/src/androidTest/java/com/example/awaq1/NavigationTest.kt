import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.awaq1.MainActivity
import org.junit.Rule
import org.junit.Test

// Requerimientos

// Para ejecutar la prueba se debe de desabilitar el Login de la aplicación
// Se desactiva al cambiar la linea 68 del archivo PrincipalView.kt
// Se cambia de if (loggedIn) { a if (true) {
// Se desactiva al cambiar la linea 67 del archivo PrincipalView.kt
// Se debe de quitar las lineas de comentado para que se ejecute el codigo

// ¡Importante!
// Se debe de iniciar la app y aceptar los permisos antes de correr la prueba.
// No se puede cerrar o terminar la app antes de empezar la prueba o los anuncios
// de permiso bloquearan la prueba y no funcionara.

// Nota, se puede interactuar con la alerta durante la prueba para cancelar la alerta

// Descripcion

// La prueba BottomNavigationBarTest corre tres diferentes pruebas en donde
// intenta navegar en la aplicacion desde la Home page. Las pruebas checan que
// se pueda mover de home a los formularios y a los ajustes.

class BottomNavigationBarTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testHomeButtonNavigatesToHome() {
        // Simulate navigating to the Home screen
        composeTestRule.onNodeWithContentDescription("Home").performClick()

        Thread.sleep(1000)

        // Wait for the UI to settle
        composeTestRule.runOnIdle { }

        // Verify that the displayed screen is Home
        composeTestRule.onNodeWithText("Hola, example!").assertIsDisplayed() // For Home screen    }
    }

    @Test
    fun testReporteButtonNavigatesToReporte() {
        // Simulate navigating to the Reporte screen
        composeTestRule.onNodeWithContentDescription("Reporte").performClick()

        Thread.sleep(1000)

        // Wait for the UI to settle
        composeTestRule.runOnIdle { }

        // Verify that the displayed screen is Reporte
        composeTestRule.onNodeWithText("Fauna en Transectos").assertIsDisplayed() // For Reporte screen
    }

    @Test
    fun testSettingsButtonNavigatesToSettings() {
        // Simulate navigating to the Settings screen
        composeTestRule.onNodeWithContentDescription("Settings").performClick()

        Thread.sleep(1000)

        // Wait for the UI to settle
        composeTestRule.runOnIdle { }

        // Verify that the displayed screen is Settings
        composeTestRule.onNodeWithText("Configuración").assertIsDisplayed() // For Settings screen
    }
}