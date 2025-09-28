package com.example.awaq1.navigator
import UserSettingsScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.awaq1.view.AboutUs
import com.example.awaq1.view.Home
import com.example.awaq1.view.Mapa
import com.example.awaq1.view.ObservationForm
import com.example.awaq1.view.ObservationFormCinco
import com.example.awaq1.view.ObservationFormDos
import com.example.awaq1.view.ObservationFormCuatro
import com.example.awaq1.view.ObservationFormSeis
import com.example.awaq1.view.ObservationFormSiete
import com.example.awaq1.view.ObservationFormTres
import com.example.awaq1.view.ObservationListScreen
import com.example.awaq1.view.SelectFormularioScreen
import com.example.awaq1.view.Settings
import com.example.awaq1.view.TwoFactor
import com.example.awaq1.view.Busqueda
import kotlinx.serialization.Serializable

@Serializable data class FormUnoID(val form_id: Long = 0)

@Serializable data class FormDosID(val form_id: Long = 0)

@Serializable data class FormTresID(val form_id: Long = 0)

@Serializable data class FormCuatroID(val form_id: Long = 0)

@Serializable data class FormCincoID(val form_id: Long = 0)

@Serializable data class FormSeisID(val form_id: Long = 0)

@Serializable data class FormSieteID(val form_id: Long = 0)

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun AppNavigator(onLogout: () -> Unit, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    //val startDestination = if (loggedIn) "home" else "log_in"
    val startDestination = "home"
    NavHost(navController = navController, startDestination = startDestination) {
//        composable("log_in") {
//            LogIn(navController = navController)
//        }
        composable("two_factor") {
            TwoFactor(navController = navController)
        }
        composable("mapa") {
            Mapa(navController = navController)
        }
        composable("settings") {
            // Logout is through the settings page
            Settings(navController = navController, onLogout)
        }
        composable("perfil") {
            UserSettingsScreen(navController = navController)
        }
        composable("busqueda"){
            Busqueda(navController = navController)
        }

        composable("elegir_reporte") {
            SelectFormularioScreen(navController = navController)
        }
        composable("about_us") {
            AboutUs(navController = navController)
        }
        composable <FormUnoID> { backStackEntry ->
            val formId: FormUnoID = backStackEntry.toRoute()
            ObservationForm(navController = navController, formId.form_id)
        }

        composable<FormDosID> { backStackEntry ->
            val formId: FormDosID = backStackEntry.toRoute()
            ObservationFormDos(navController = navController, formId.form_id)
        }
        composable<FormTresID> { backStackEntry ->
            val formId: FormTresID = backStackEntry.toRoute()
           ObservationFormTres(navController = navController, formId.form_id)
        }
        composable<FormCuatroID> { backStackEntry ->
            val formId: FormCuatroID = backStackEntry.toRoute()
            ObservationFormCuatro(navController = navController, formId.form_id)
        }
        composable<FormCincoID> { backStackEntry ->
            val formId: FormCincoID = backStackEntry.toRoute()
            ObservationFormCinco(navController = navController, formId.form_id)
        }
        composable<FormSeisID> { backStackEntry ->
            val formId: FormSeisID = backStackEntry.toRoute()
            ObservationFormSeis(navController = navController, formId.form_id)
        }
        composable<FormSieteID> { backStackEntry ->
            val formId: FormSieteID = backStackEntry.toRoute()
            ObservationFormSiete(navController = navController, formId.form_id)
        }
        composable("buscar") {
            ObservationListScreen(navController = navController)
        }
        composable("home") {
            Home(navController = navController)
        }
    }
}
