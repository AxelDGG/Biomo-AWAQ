package com.example.awaq1.data.usuario

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.awaq1.data.formularios.FormularioCincoEntity
import com.example.awaq1.data.formularios.FormularioCuatroEntity
import com.example.awaq1.data.formularios.FormularioDosEntity
import com.example.awaq1.data.formularios.FormularioSeisEntity
import com.example.awaq1.data.formularios.FormularioSieteEntity
import com.example.awaq1.data.formularios.FormularioTresEntity
import com.example.awaq1.data.formularios.FormularioUnoEntity
import kotlinx.coroutines.flow.Flow


// DAO for UsuarioEntity
@Dao
interface UsuarioDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(usuario: UsuarioEntity): Long

    @Update
    suspend fun update(usuario: UsuarioEntity)

    @Delete
    suspend fun delete(usuario: UsuarioEntity)

    @Query("SELECT * from Usuarios WHERE id = :id")
    fun getUsuarioById(id: Long): Flow<UsuarioEntity?>

    @Query("SELECT * from Usuarios ORDER BY id ASC")
    fun getAllUsuarios(): Flow<List<UsuarioEntity>>

    @Query("SELECT id FROM Usuarios WHERE username = :username")
    suspend fun getUserIdByUsername(username: String): Long?

    @Query("UPDATE Usuarios SET lastAccess = :lastAccess WHERE id = :userId")
    suspend fun updateLastAccess(userId: Long, lastAccess: String)

    @Query("UPDATE Usuarios SET lastLogin = :lastLogin WHERE id = :userId")
    suspend fun updateLastLogin(userId: Long, lastLogin: String)

    // Funciones para manejar la contraseña y el salt de la tabla

    @Query("UPDATE Usuarios SET passwordHash = :passwordHash WHERE id = :userId")
    suspend fun updatePassword(userId: Long, passwordHash: String)

    /*
    @Query("UPDATE Usuarios SET salt = :salt WHERE id = :userId")
    suspend fun updateSalt(userId: Long, salt: String)

     */

    //Funcion para obtener la contraseña y el salt de la tabla
    @Query("SELECT passwordHash FROM Usuarios WHERE username = :username")
    suspend fun getUserCredentials(username: String): String

}

// DAO for UsuarioFormulario1Entity
@Dao
interface UsuarioFormulario1DAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(usuarioFormulario: UsuarioFormulario1Entity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFromParts(usuario: UsuarioEntity, formulario: FormularioUnoEntity)

    @Update
    suspend fun update(usuarioFormulario: UsuarioFormulario1Entity)

    @Delete
    suspend fun delete(usuarioFormulario: UsuarioFormulario1Entity)

    @Query("SELECT * from UsuarioFormulario1 WHERE usuarioId = :usuarioId")
    fun getFormulariosForUsuario(usuarioId: Long): Flow<List<UsuarioFormulario1Entity>>

    @Query("SELECT * from UsuarioFormulario1 WHERE formId = :formId")
    fun getUsuariosForFormulario(formId: Long): Flow<List<UsuarioFormulario1Entity>>

    @Query("SELECT * from Formulario1 INNER JOIN UsuarioFormulario1 ON Formulario1.id = UsuarioFormulario1.formId WHERE UsuarioFormulario1.usuarioId = :usuarioId")
    fun getAllFormulariosForUserID(usuarioId: Long): Flow<List<FormularioUnoEntity>>
}

// DAO for UsuarioFormulario2Entity
@Dao
interface UsuarioFormulario2DAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(usuarioFormulario: UsuarioFormulario2Entity): Long

    @Update
    suspend fun update(usuarioFormulario: UsuarioFormulario2Entity)

    @Delete
    suspend fun delete(usuarioFormulario: UsuarioFormulario2Entity)

    @Query("SELECT * from UsuarioFormulario2 WHERE usuarioId = :usuarioId")
    fun getFormulariosForUsuario(usuarioId: Long): Flow<List<UsuarioFormulario2Entity>>

    @Query("SELECT * from UsuarioFormulario2 WHERE formId = :formId")
    fun getUsuariosForFormulario(formId: Long): Flow<List<UsuarioFormulario2Entity>>

    @Query("SELECT * from Formulario2 INNER JOIN UsuarioFormulario2 ON Formulario2.id = UsuarioFormulario2.formId WHERE UsuarioFormulario2.usuarioId = :usuarioId")
    fun getAllFormulariosForUserID(usuarioId: Long): Flow<List<FormularioDosEntity>>
}

// DAO for UsuarioFormulario3Entity
@Dao
interface UsuarioFormulario3DAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(usuarioFormulario: UsuarioFormulario3Entity): Long

    @Update
    suspend fun update(usuarioFormulario: UsuarioFormulario3Entity)

    @Delete
    suspend fun delete(usuarioFormulario: UsuarioFormulario3Entity)

    @Query("SELECT * from UsuarioFormulario3 WHERE usuarioId = :usuarioId")
    fun getFormulariosForUsuario(usuarioId: Long): Flow<List<UsuarioFormulario3Entity>>

    @Query("SELECT * from UsuarioFormulario3 WHERE formId = :formId")
    fun getUsuariosForFormulario(formId: Long): Flow<List<UsuarioFormulario3Entity>>

    @Query("SELECT * from Formulario3 INNER JOIN UsuarioFormulario3 ON Formulario3.id = UsuarioFormulario3.formId WHERE UsuarioFormulario3.usuarioId = :usuarioId")
    fun getAllFormulariosForUserID(usuarioId: Long): Flow<List<FormularioTresEntity>>
}

// DAO for UsuarioFormulario4Entity
@Dao
interface UsuarioFormulario4DAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(usuarioFormulario: UsuarioFormulario4Entity): Long

    @Update
    suspend fun update(usuarioFormulario: UsuarioFormulario4Entity)

    @Delete
    suspend fun delete(usuarioFormulario: UsuarioFormulario4Entity)

    @Query("SELECT * from UsuarioFormulario4 WHERE usuarioId = :usuarioId")
    fun getFormulariosForUsuario(usuarioId: Long): Flow<List<UsuarioFormulario4Entity>>

    @Query("SELECT * from UsuarioFormulario4 WHERE formId = :formId")
    fun getUsuariosForFormulario(formId: Long): Flow<List<UsuarioFormulario4Entity>>

    @Query("SELECT * from Formulario4 INNER JOIN UsuarioFormulario4 ON Formulario4.id = UsuarioFormulario4.formId WHERE UsuarioFormulario4.usuarioId = :usuarioId")
    fun getAllFormulariosForUserID(usuarioId: Long): Flow<List<FormularioCuatroEntity>>
}

// DAO for UsuarioFormulario5Entity
@Dao
interface UsuarioFormulario5DAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(usuarioFormulario: UsuarioFormulario5Entity): Long

    @Update
    suspend fun update(usuarioFormulario: UsuarioFormulario5Entity)

    @Delete
    suspend fun delete(usuarioFormulario: UsuarioFormulario5Entity)

    @Query("SELECT * from UsuarioFormulario5 WHERE usuarioId = :usuarioId")
    fun getFormulariosForUsuario(usuarioId: Long): Flow<List<UsuarioFormulario5Entity>>

    @Query("SELECT * from UsuarioFormulario5 WHERE formId = :formId")
    fun getUsuariosForFormulario(formId: Long): Flow<List<UsuarioFormulario5Entity>>

    @Query("SELECT * from Formulario5 INNER JOIN UsuarioFormulario5 ON Formulario5.id = UsuarioFormulario5.formId WHERE UsuarioFormulario5.usuarioId = :usuarioId")
    fun getAllFormulariosForUserID(usuarioId: Long): Flow<List<FormularioCincoEntity>>
}

// DAO for UsuarioFormulario6Entity
@Dao
interface UsuarioFormulario6DAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(usuarioFormulario: UsuarioFormulario6Entity): Long

    @Update
    suspend fun update(usuarioFormulario: UsuarioFormulario6Entity)

    @Delete
    suspend fun delete(usuarioFormulario: UsuarioFormulario6Entity)

    @Query("SELECT * from UsuarioFormulario6 WHERE usuarioId = :usuarioId")
    fun getFormulariosForUsuario(usuarioId: Long): Flow<List<UsuarioFormulario6Entity>>

    @Query("SELECT * from UsuarioFormulario6 WHERE formId = :formId")
    fun getUsuariosForFormulario(formId: Long): Flow<List<UsuarioFormulario6Entity>>

    @Query("SELECT * from Formulario6 INNER JOIN UsuarioFormulario6 ON Formulario6.id = UsuarioFormulario6.formId WHERE UsuarioFormulario6.usuarioId = :usuarioId")
    fun getAllFormulariosForUserID(usuarioId: Long): Flow<List<FormularioSeisEntity>>
}

// DAO for UsuarioFormulario7Entity
@Dao
interface UsuarioFormulario7DAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(usuarioFormulario: UsuarioFormulario7Entity): Long

    @Update
    suspend fun update(usuarioFormulario: UsuarioFormulario7Entity)

    @Delete
    suspend fun delete(usuarioFormulario: UsuarioFormulario7Entity)

    @Query("SELECT * from UsuarioFormulario7 WHERE usuarioId = :usuarioId")
    fun getFormulariosForUsuario(usuarioId: Long): Flow<List<UsuarioFormulario7Entity>>

    @Query("SELECT * from UsuarioFormulario7 WHERE formId = :formId")
    fun getUsuariosForFormulario(formId: Long): Flow<List<UsuarioFormulario7Entity>>

    @Query("SELECT * from Formulario7 INNER JOIN UsuarioFormulario7 ON Formulario7.id = UsuarioFormulario7.formId WHERE UsuarioFormulario7.usuarioId = :usuarioId")
    fun getAllFormulariosForUserID(usuarioId: Long): Flow<List<FormularioSieteEntity>>
}