package com.example.awaq1.data

import android.content.Context
import com.example.awaq1.data.formularios.FormulariosRepository
import com.example.awaq1.data.formularios.OfflineFormulariosRepository
import com.example.awaq1.data.formularios.remote.FormsRemoteRepository
import com.example.awaq1.data.usuario.UsuariosRepository
import com.example.awaq1.data.formularios.local.TokenManager
import com.example.awaq1.data.formularios.remote.AuthApiService
import com.example.awaq1.data.formularios.remote.RetroFitClient
import com.example.awaq1.data.formularios.local.RegistroEnvioRepository

// Extiende la interfaz para exponer el repo nuevo
interface AppContainer {
    val formulariosRepository: FormulariosRepository
    val formulariosRemoteRepository: FormsRemoteRepository
    val usuariosRepository: UsuariosRepository
    val registroEnvioRepository: RegistroEnvioRepository      // 👈 NUEVO
}

class AppDataContainer(private val context: Context) : AppContainer {

    // Reutiliza una sola instancia de DB
    private val db by lazy { FormulariosDatabase.getDatabase(context) }

    private val tokenManager by lazy { TokenManager(context) }
    private val apiService: AuthApiService by lazy { RetroFitClient.create(tokenManager) }

    // 👇 NUEVO: repo para marcar enviados
    override val registroEnvioRepository: RegistroEnvioRepository by lazy {
        RegistroEnvioRepository(db.registroEnvioDAO())
    }

    override val formulariosRepository: FormulariosRepository by lazy {
        OfflineFormulariosRepository(
            formularioUnoDAO = db.formulario1Dao(),
            formularioDosDAO = db.formulario2Dao(),
            formularioTresDAO = db.formulario3Dao(),
            formularioCuatroDAO = db.formulario4Dao(),
            formularioCincoDAO = db.formulario5Dao(),
            formularioSeisDAO = db.formulario6Dao(),
            formularioSieteDAO = db.formulario7Dao(),
            imageDAO = db.imageDao()
        )
    }

    override val usuariosRepository: UsuariosRepository by lazy {
        UsuariosRepository(
            usuarioDAO = db.usuarioDAO(),
            usuarioFormulario1DAO = db.usuarioFormulario1DAO(),
            usuarioFormulario2DAO = db.usuarioFormulario2DAO(),
            usuarioFormulario3DAO = db.usuarioFormulario3DAO(),
            usuarioFormulario4DAO = db.usuarioFormulario4DAO(),
            usuarioFormulario5DAO = db.usuarioFormulario5DAO(),
            usuarioFormulario6DAO = db.usuarioFormulario6DAO(),
            usuarioFormulario7DAO = db.usuarioFormulario7DAO(),
            formularioUnoDAO = db.formulario1Dao(),
            formularioDosDAO = db.formulario2Dao(),
            formularioTresDAO = db.formulario3Dao(),
            formularioCuatroDAO = db.formulario4Dao(),
            formularioCincoDAO = db.formulario5Dao(),
            formularioSeisDAO = db.formulario6Dao(),
            formularioSieteDAO = db.formulario7Dao(),
        )
    }

    override val formulariosRemoteRepository: FormsRemoteRepository by lazy {
        FormsRemoteRepository(apiService, registroEnvioRepository)
    }

}
