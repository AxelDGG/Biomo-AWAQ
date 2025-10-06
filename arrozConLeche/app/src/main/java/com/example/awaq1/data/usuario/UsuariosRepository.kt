package com.example.awaq1.data.usuario

import androidx.room.Transaction
import com.example.awaq1.data.formularios.FormularioCincoDAO
import com.example.awaq1.data.formularios.FormularioCincoEntity
import com.example.awaq1.data.formularios.FormularioCuatroDAO
import com.example.awaq1.data.formularios.FormularioCuatroEntity
import com.example.awaq1.data.formularios.FormularioDosDAO
import com.example.awaq1.data.formularios.FormularioDosEntity
import com.example.awaq1.data.formularios.FormularioSeisDAO
import com.example.awaq1.data.formularios.FormularioSeisEntity
import com.example.awaq1.data.formularios.FormularioSieteDAO
import com.example.awaq1.data.formularios.FormularioSieteEntity
import com.example.awaq1.data.formularios.FormularioTresDAO
import com.example.awaq1.data.formularios.FormularioTresEntity
import com.example.awaq1.data.formularios.FormularioUnoDAO
import com.example.awaq1.data.formularios.FormularioUnoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

// TODO: getAllFormulario{DOS,TRES,CUATRO}ForUserID(usuarioId: long)
// TODO: getFormulario{UNO,DOS,TRES,CUATRO}CountForUsuarioId(usuarioId: long)

// Repository for handling user-related operations, including associations with formularios
class UsuariosRepository(
    private val usuarioDAO: UsuarioDAO,
    private val formularioUnoDAO: FormularioUnoDAO,
    private val usuarioFormulario1DAO: UsuarioFormulario1DAO,
    private val formularioDosDAO: FormularioDosDAO,
    private val usuarioFormulario2DAO: UsuarioFormulario2DAO,
    private val formularioTresDAO: FormularioTresDAO,
    private val usuarioFormulario3DAO: UsuarioFormulario3DAO,
    private val formularioCuatroDAO: FormularioCuatroDAO,
    private val usuarioFormulario4DAO: UsuarioFormulario4DAO,
    private val formularioCincoDAO: FormularioCincoDAO,
    private val usuarioFormulario5DAO: UsuarioFormulario5DAO,
    private val formularioSeisDAO: FormularioSeisDAO,
    private val usuarioFormulario6DAO: UsuarioFormulario6DAO,
    private val formularioSieteDAO: FormularioSieteDAO,
    private val usuarioFormulario7DAO: UsuarioFormulario7DAO,
) {

    // User operations
    suspend fun insertUser(usuario: UsuarioEntity): Long = usuarioDAO.insert(usuario)

    suspend fun updateUser(usuario: UsuarioEntity) = usuarioDAO.update(usuario)

    suspend fun deleteUser(usuario: UsuarioEntity) = usuarioDAO.delete(usuario)

    fun getUserById(id: Long): Flow<UsuarioEntity?> = usuarioDAO.getUsuarioById(id)

    fun getAllUsers(): Flow<List<UsuarioEntity>> = usuarioDAO.getAllUsuarios()

    suspend fun getUserIdByUsername(username: String): Long? = usuarioDAO.getUserIdByUsername(username)

    suspend fun updateLastAccess(userId: Long, lastAccess: String) = usuarioDAO.updateLastAccess(userId, lastAccess)

    suspend fun updateLastLogin(userId: Long, lastLogin: String) = usuarioDAO.updateLastLogin(userId, lastLogin)


    // UsuarioFormulario1 operations (associations)
    @Transaction
    suspend fun insertUserWithFormularioUno(userId: Long, formulario: FormularioUnoEntity): Long {
        val formId = formularioUnoDAO.insert(formulario)
        if (formId == -1L) throw Exception("Failed to insert form")

        val usuarioFormulario = UsuarioFormulario1Entity(usuarioId = userId, formId = formId)
        usuarioFormulario1DAO.insert(usuarioFormulario)

        return formId
    }

    fun getFormulariosForUsuarioUno(usuarioId: Long): Flow<List<UsuarioFormulario1Entity>> = usuarioFormulario1DAO.getFormulariosForUsuario(usuarioId)

    fun getUsuariosForFormularioUno(formId: Long): Flow<List<UsuarioFormulario1Entity>> = usuarioFormulario1DAO.getUsuariosForFormulario(formId)

    fun getAllFormularioUnoForUserID(usuarioId: Long): Flow<List<FormularioUnoEntity>> = usuarioFormulario1DAO.getAllFormulariosForUserID(usuarioId)

    // UsuarioFormulario2 operations
    @Transaction
    suspend fun insertUserWithFormularioDos(userId: Long, formulario: FormularioDosEntity): Long {
        val formId = formularioDosDAO.insert(formulario)
        if (formId == -1L) throw Exception("Failed to insert form")

        val usuarioFormulario = UsuarioFormulario2Entity(usuarioId = userId, formId = formId)
        usuarioFormulario2DAO.insert(usuarioFormulario)
        return formId
    }

    fun getFormulariosForUsuarioDos(usuarioId: Long): Flow<List<UsuarioFormulario2Entity>> = usuarioFormulario2DAO.getFormulariosForUsuario(usuarioId)

    fun getUsuariosForFormularioDos(formId: Long): Flow<List<UsuarioFormulario2Entity>> = usuarioFormulario2DAO.getUsuariosForFormulario(formId)

    fun getAllFormularioDosForUserID(usuarioId: Long): Flow<List<FormularioDosEntity>> = usuarioFormulario2DAO.getAllFormulariosForUserID(usuarioId)

    // UsuarioFormulario3 operations
    @Transaction
    suspend fun insertUserWithFormularioTres(userId: Long, formulario: FormularioTresEntity): Long {
        val formId = formularioTresDAO.insert(formulario)
        if (formId == -1L) throw Exception("Failed to insert form")

        val usuarioFormulario = UsuarioFormulario3Entity(usuarioId = userId, formId = formId)
        usuarioFormulario3DAO.insert(usuarioFormulario)
        return formId
    }

    fun getFormulariosForUsuarioTres(usuarioId: Long): Flow<List<UsuarioFormulario3Entity>> = usuarioFormulario3DAO.getFormulariosForUsuario(usuarioId)

    fun getUsuariosForFormularioTres(formId: Long): Flow<List<UsuarioFormulario3Entity>> = usuarioFormulario3DAO.getUsuariosForFormulario(formId)

    fun getAllFormularioTresForUserID(usuarioId: Long): Flow<List<FormularioTresEntity>> = usuarioFormulario3DAO.getAllFormulariosForUserID(usuarioId)

    // UsuarioFormulario4 operations
    @Transaction
    suspend fun insertUserWithFormularioCuatro(userId: Long, formulario: FormularioCuatroEntity): Long {
        val formId = formularioCuatroDAO.insert(formulario)
        if (formId == -1L) throw Exception("Failed to insert form")

        val usuarioFormulario = UsuarioFormulario4Entity(usuarioId = userId, formId = formId)
        usuarioFormulario4DAO.insert(usuarioFormulario)
        return formId
    }

    fun getFormulariosForUsuarioCuatro(usuarioId: Long): Flow<List<UsuarioFormulario4Entity>> = usuarioFormulario4DAO.getFormulariosForUsuario(usuarioId)

    fun getUsuariosForFormularioCuatro(formId: Long): Flow<List<UsuarioFormulario4Entity>> = usuarioFormulario4DAO.getUsuariosForFormulario(formId)

    fun getAllFormularioCuatroForUserID(usuarioId: Long): Flow<List<FormularioCuatroEntity>> = usuarioFormulario4DAO.getAllFormulariosForUserID(usuarioId)

    // UsuarioFormulario5 operations
    @Transaction
    suspend fun insertUserWithFormularioCinco(userId: Long, formulario: FormularioCincoEntity): Long {
        val formId = formularioCincoDAO.insert(formulario)
        if (formId == -1L) throw Exception("Failed to insert form")

        val usuarioFormulario = UsuarioFormulario5Entity(usuarioId = userId, formId = formId)
        usuarioFormulario5DAO.insert(usuarioFormulario)
        return formId
    }

    fun getFormulariosForUsuarioCinco(usuarioId: Long): Flow<List<UsuarioFormulario5Entity>> = usuarioFormulario5DAO.getFormulariosForUsuario(usuarioId)

    fun getUsuariosForFormularioCinco(formId: Long): Flow<List<UsuarioFormulario5Entity>> = usuarioFormulario5DAO.getUsuariosForFormulario(formId)

    fun getAllFormularioCincoForUserID(usuarioId: Long): Flow<List<FormularioCincoEntity>> = usuarioFormulario5DAO.getAllFormulariosForUserID(usuarioId)

    // UsuarioFormulario6 operations
    @Transaction
    suspend fun insertUserWithFormularioSeis(userId: Long, formulario: FormularioSeisEntity): Long {
        val formId = formularioSeisDAO.insert(formulario)
        if (formId == -1L) throw Exception("Failed to insert form")

        val usuarioFormulario = UsuarioFormulario6Entity(usuarioId = userId, formId = formId)
        usuarioFormulario6DAO.insert(usuarioFormulario)
        return formId
    }

    fun getFormulariosForUsuarioSeis(usuarioId: Long): Flow<List<UsuarioFormulario6Entity>> = usuarioFormulario6DAO.getFormulariosForUsuario(usuarioId)

    fun getUsuariosForFormularioSeis(formId: Long): Flow<List<UsuarioFormulario6Entity>> = usuarioFormulario6DAO.getUsuariosForFormulario(formId)

    fun getAllFormularioSeisForUserID(usuarioId: Long): Flow<List<FormularioSeisEntity>> = usuarioFormulario6DAO.getAllFormulariosForUserID(usuarioId)

    // UsuarioFormulario7 operations
    @Transaction
    suspend fun insertUserWithFormularioSiete(userId: Long, formulario: FormularioSieteEntity): Long {
        val formId = formularioSieteDAO.insert(formulario)
        if (formId == -1L) throw Exception("Failed to insert form")

        val usuarioFormulario = UsuarioFormulario7Entity(usuarioId = userId, formId = formId)
        usuarioFormulario7DAO.insert(usuarioFormulario)
        return formId
    }

    fun getFormulariosForUsuarioSiete(usuarioId: Long): Flow<List<UsuarioFormulario7Entity>> = usuarioFormulario7DAO.getFormulariosForUsuario(usuarioId)

    fun getUsuariosForFormularioSiete(formId: Long): Flow<List<UsuarioFormulario7Entity>> = usuarioFormulario7DAO.getUsuariosForFormulario(formId)

    fun getAllFormularioSieteForUserID(usuarioId: Long): Flow<List<FormularioSieteEntity>> = usuarioFormulario7DAO.getAllFormulariosForUserID(usuarioId)
}
