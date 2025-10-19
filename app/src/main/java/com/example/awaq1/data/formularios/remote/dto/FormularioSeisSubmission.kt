package com.example.awaq1.data.formularios.remote.dto

import com.example.awaq1.data.formularios.FormularioSeisEntity
import com.google.gson.annotations.SerializedName

data class FormularioSeisSubmission(
    @SerializedName("codigo") val codigo: String,
    @SerializedName("clima") val clima: String,
    @SerializedName("temporada") val temporada: String,
    @SerializedName("zona") val zona: String,
    @SerializedName("nombrecamara") val nombreCamara: String,
    @SerializedName("placacamara") val placaCamara: String,
    @SerializedName("placaguaya") val placaGuaya: String,
    @SerializedName("anchocamino") val anchoCamino: String,
    @SerializedName("fechainstalacion") val fechaInstalacion: String,
    @SerializedName("distanciaobjetivo") val distanciaObjetivo: String,
    @SerializedName("alturalente") val alturaLente: String,
    @SerializedName("checklist") val checklist: String,
    @SerializedName("observaciones") val observaciones: String,
    @SerializedName("latitude") val latitude: Double? = null,
    @SerializedName("longitude") val longitude: Double? = null,
    @SerializedName("fecha") val fecha: String,
    @SerializedName("editado") val editado: String,
    @SerializedName("id_usuario") val idUsuario: Int? = null
)

fun FormularioSeisEntity.toSubmission(idUsuarioToken: Int? = null): FormularioSeisSubmission =
    FormularioSeisSubmission(
        codigo = codigo,
        clima = clima,
        temporada = temporada,
        zona = zona,
        nombreCamara = nombreCamara,
        placaCamara = placaCamara,
        placaGuaya = placaGuaya,
        anchoCamino = anchoCamino,
        fechaInstalacion = fechaInstalacion,
        distanciaObjetivo = distanciaObjetivo,
        alturaLente = alturaLente,
        checklist = checklist,
        observaciones = observaciones,
        latitude = latitude,
        longitude = longitude,
        fecha = fecha,
        editado = editado,
        idUsuario = idUsuarioToken
    )
