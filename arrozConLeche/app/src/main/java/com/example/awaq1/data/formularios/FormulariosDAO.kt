package com.example.awaq1.data.formularios

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

// TODO: OnConflictStrategy.REPLACE para todos los DAOs
//  (Cuando funcione en pruebas la edicion de reportes de FormularioUno)
@Dao
interface FormularioUnoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: FormularioUnoEntity): Long

    @Update
    suspend fun update(item: FormularioUnoEntity)

    @Delete
    suspend fun delete(item: FormularioUnoEntity)

    @Query("SELECT * from Formulario1 WHERE id = :id")
    fun getFormularioUnoEntity(id: Long): Flow<FormularioUnoEntity?>

    @Query("SELECT * from Formulario1 ORDER BY id ASC")
    fun getAllFormularioUnoEntities(): Flow<List<FormularioUnoEntity>>

    @Query("SELECT COUNT(*) FROM Formulario1")
    suspend fun getFormularioUnoCount(): Int
}

@Dao
interface FormularioDosDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: FormularioDosEntity): Long

    @Update
    suspend fun update(item: FormularioDosEntity)

    @Delete
    suspend fun delete(item: FormularioDosEntity)

    @Query("SELECT * from Formulario2 WHERE id = :id")
    fun getFormularioDosEntity(id: Long): Flow<FormularioDosEntity?>

    @Query("SELECT * from Formulario2 ORDER BY id ASC")
    fun getAllFormularioDosEntities(): Flow<List<FormularioDosEntity>>

    @Query("SELECT COUNT(*) FROM Formulario2")
    suspend fun getFormularioDosCount(): Int
}

@Dao
interface FormularioTresDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: FormularioTresEntity): Long

    @Update
    suspend fun update(item: FormularioTresEntity)

    @Delete
    suspend fun delete(item: FormularioTresEntity)

    @Query("SELECT * from Formulario3 WHERE id = :id")
    fun getFormularioTresEntity(id: Long): Flow<FormularioTresEntity?>

    @Query("SELECT * from Formulario3 ORDER BY id ASC")
    fun getAllFormularioTresEntities(): Flow<List<FormularioTresEntity>>

    @Query("SELECT COUNT(*) FROM Formulario3")
    suspend fun getFormularioTresCount(): Int
}

@Dao
interface FormularioCuatroDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: FormularioCuatroEntity): Long

    @Update
    suspend fun update(item: FormularioCuatroEntity)

    @Delete
    suspend fun delete(item: FormularioCuatroEntity)

    @Query("SELECT * from Formulario4 WHERE id = :id")
    fun getFormularioCuatroEntity(id: Long): Flow<FormularioCuatroEntity?>

    @Query("SELECT * from Formulario4 ORDER BY id ASC")
    fun getAllFormularioCuatroEntities(): Flow<List<FormularioCuatroEntity>>

    @Query("SELECT COUNT(*) FROM Formulario4")
    suspend fun getFormularioCuatroCount(): Int
}

@Dao
interface FormularioCincoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: FormularioCincoEntity): Long

    @Update
    suspend fun update(item: FormularioCincoEntity)

    @Delete
    suspend fun delete(item: FormularioCincoEntity)

    @Query("SELECT * from Formulario5 WHERE id = :id")
    fun getFormularioCincoEntity(id: Long): Flow<FormularioCincoEntity?>

    @Query("SELECT * from Formulario5 ORDER BY id ASC")
    fun getAllFormularioCincoEntities(): Flow<List<FormularioCincoEntity>>

    @Query("SELECT COUNT(*) FROM Formulario5")
    suspend fun getFormularioCincoCount(): Int
}

@Dao
interface FormularioSeisDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: FormularioSeisEntity): Long

    @Update
    suspend fun update(item: FormularioSeisEntity)

    @Delete
    suspend fun delete(item: FormularioSeisEntity)

    @Query("SELECT * from Formulario6 WHERE id = :id")
    fun getFormularioSeisEntity(id: Long): Flow<FormularioSeisEntity?>

    @Query("SELECT * from Formulario6 ORDER BY id ASC")
    fun getAllFormularioSeisEntities(): Flow<List<FormularioSeisEntity>>

    @Query("SELECT COUNT(*) FROM Formulario6")
    suspend fun getFormularioSeisCount(): Int
}

@Dao
interface FormularioSieteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: FormularioSieteEntity): Long

    @Update
    suspend fun update(item: FormularioSieteEntity)

    @Delete
    suspend fun delete(item: FormularioSieteEntity)

    @Query("SELECT * from Formulario7 WHERE id = :id")
    fun getFormularioSieteEntity(id: Long): Flow<FormularioSieteEntity?>

    @Query("SELECT * from Formulario7 ORDER BY id ASC")
    fun getAllFormularioSieteEntities(): Flow<List<FormularioSieteEntity>>

    @Query("SELECT COUNT(*) FROM Formulario7")
    suspend fun getFormularioSieteCount(): Int
}