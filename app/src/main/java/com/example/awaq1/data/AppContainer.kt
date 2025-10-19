package com.example.awaq1.data

import android.content.Context
import com.example.awaq1.data.formularios.FormulariosRepository
import com.example.awaq1.data.formularios.OfflineFormulariosRepository
import com.example.awaq1.data.formularios.remote.FormsRemoteRepository
import com.example.awaq1.data.usuario.UsuariosRepository
import com.example.awaq1.data.formularios.local.TokenManager
import com.example.awaq1.data.formularios.remote.AuthApiService
import com.example.awaq1.data.formularios.remote.RetroFitClient

// Esto luego lo extendemos con los otros repositorios
interface AppContainer {
    val formulariosRepository: FormulariosRepository
    val formulariosRemoteRepository: FormsRemoteRepository
    val usuariosRepository: UsuariosRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    private val tokenManager by lazy{TokenManager(context)}
    private val apiService: AuthApiService by lazy {
        RetroFitClient.create(tokenManager)
    }

    override val formulariosRepository: FormulariosRepository by lazy {
        OfflineFormulariosRepository(
            formularioUnoDAO = FormulariosDatabase.getDatabase(context).formulario1Dao(),
            formularioDosDAO = FormulariosDatabase.getDatabase(context).formulario2Dao(),
            formularioTresDAO = FormulariosDatabase.getDatabase(context).formulario3Dao(),
            formularioCuatroDAO = FormulariosDatabase.getDatabase(context).formulario4Dao(),
            formularioCincoDAO = FormulariosDatabase.getDatabase(context).formulario5Dao(),
            formularioSeisDAO = FormulariosDatabase.getDatabase(context).formulario6Dao(),
            formularioSieteDAO = FormulariosDatabase.getDatabase(context).formulario7Dao(),
            imageDAO = FormulariosDatabase.getDatabase(context).imageDao()
        )
    }

    override val usuariosRepository: UsuariosRepository by lazy {
        UsuariosRepository(
            usuarioDAO = FormulariosDatabase.getDatabase(context).usuarioDAO(),
            usuarioFormulario1DAO = FormulariosDatabase.getDatabase(context).usuarioFormulario1DAO(),
            usuarioFormulario2DAO = FormulariosDatabase.getDatabase(context).usuarioFormulario2DAO(),
            usuarioFormulario3DAO = FormulariosDatabase.getDatabase(context).usuarioFormulario3DAO(),
            usuarioFormulario4DAO = FormulariosDatabase.getDatabase(context).usuarioFormulario4DAO(),
            usuarioFormulario5DAO = FormulariosDatabase.getDatabase(context).usuarioFormulario5DAO(),
            usuarioFormulario6DAO = FormulariosDatabase.getDatabase(context).usuarioFormulario6DAO(),
            usuarioFormulario7DAO = FormulariosDatabase.getDatabase(context).usuarioFormulario7DAO(),
            formularioUnoDAO = FormulariosDatabase.getDatabase(context).formulario1Dao(),
            formularioDosDAO = FormulariosDatabase.getDatabase(context).formulario2Dao(),
            formularioTresDAO = FormulariosDatabase.getDatabase(context).formulario3Dao(),
            formularioCuatroDAO = FormulariosDatabase.getDatabase(context).formulario4Dao(),
            formularioCincoDAO = FormulariosDatabase.getDatabase(context).formulario5Dao(),
            formularioSeisDAO = FormulariosDatabase.getDatabase(context).formulario6Dao(),
            formularioSieteDAO = FormulariosDatabase.getDatabase(context).formulario7Dao(),
        )
    }

    override val formulariosRemoteRepository: FormsRemoteRepository by lazy {
        FormsRemoteRepository(apiService)
    }
}

