package com.example.awaq1.data.formularios

import kotlinx.coroutines.flow.Flow

interface FormulariosRepository {

    // FormularioUno
    fun getAllFormularioUnosStream(): Flow<List<FormularioUnoEntity>>
    fun getFormularioUnoStream(id: Long): Flow<FormularioUnoEntity?>

    suspend fun insertFormularioUno(item: FormularioUnoEntity): Long
    suspend fun deleteFormularioUno(item: FormularioUnoEntity)
    suspend fun updateFormularioUno(item: FormularioUnoEntity)

    // FormularioDos
    fun getAllFormularioDosesStream(): Flow<List<FormularioDosEntity>>
    fun getFormularioDosStream(id: Long): Flow<FormularioDosEntity?>

    suspend fun insertFormularioDos(item: FormularioDosEntity): Long
    suspend fun deleteFormularioDos(item: FormularioDosEntity)
    suspend fun updateFormularioDos(item: FormularioDosEntity)

    // FormularioTres
    fun getAllFormularioTresStream(): Flow<List<FormularioTresEntity>>
    fun getFormularioTresStream(id: Long): Flow<FormularioTresEntity?>

    suspend fun insertFormularioTres(item: FormularioTresEntity): Long
    suspend fun deleteFormularioTres(item: FormularioTresEntity)
    suspend fun updateFormularioTres(item: FormularioTresEntity)

    // FormularioCuatro
    fun getAllFormularioCuatroStream(): Flow<List<FormularioCuatroEntity>>
    fun getFormularioCuatroStream(id: Long): Flow<FormularioCuatroEntity?>

    suspend fun insertFormularioCuatro(item: FormularioCuatroEntity): Long
    suspend fun deleteFormularioCuatro(item: FormularioCuatroEntity)
    suspend fun updateFormularioCuatro(item: FormularioCuatroEntity)

    //FormularioCinco
    fun getAllFormularioCincoStream(): Flow<List<FormularioCincoEntity>>
    fun getFormularioCincoStream(id: Long): Flow<FormularioCincoEntity?>

    suspend fun insertFormularioCinco(item: FormularioCincoEntity): Long
    suspend fun deleteFormularioCinco(item: FormularioCincoEntity)
    suspend fun updateFormularioCinco(item: FormularioCincoEntity)

    //FormularioSeis
    fun getAllFormularioSeisStream(): Flow<List<FormularioSeisEntity>>
    fun getFormularioSeisStream(id: Long): Flow<FormularioSeisEntity?>

    suspend fun insertFormularioSeis(item: FormularioSeisEntity): Long
    suspend fun deleteFormularioSeis(item: FormularioSeisEntity)
    suspend fun updateFormularioSeis(item: FormularioSeisEntity)

    //FormularioSiete
    fun getAllFormularioSieteStream(): Flow<List<FormularioSieteEntity>>
    fun getFormularioSieteStream(id: Long): Flow<FormularioSieteEntity?>

    suspend fun insertFormularioSiete(item: FormularioSieteEntity): Long
    suspend fun deleteFormularioSiete(item: FormularioSieteEntity)
    suspend fun updateFormularioSiete(item: FormularioSieteEntity)

    // General Formularios

    fun getFormularioUnoCount(): Flow<Int>
    fun getFormularioDosCount(): Flow<Int>
    fun getFormularioTresCount(): Flow<Int>
    fun getFormularioCuatroCount(): Flow<Int>
    fun getFormularioCincoCount(): Flow<Int>
    fun getFormularioSeisCount(): Flow<Int>
    fun getFormularioSieteCount(): Flow<Int>

    fun getAllFormulariosCount(): Flow<Int>

    suspend fun insertImage(image: ImageEntity)
    fun getImagesByFormulario(formularioId: Long, formularioType: String): Flow<List<ImageEntity>>
    suspend fun deleteImagesByFormulario(formularioId: Long, formularioType: String)
}
