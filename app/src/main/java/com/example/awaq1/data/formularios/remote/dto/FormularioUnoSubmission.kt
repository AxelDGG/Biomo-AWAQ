package com.example.awaq1.data.formularios.remote.dto

import com.google.gson.annotations.SerializedName
import com.example.awaq1.data.formularios.FormularioUnoEntity

data class FormularioUnoSubmission(
    @SerializedName("transecto") val transecto: String,
    @SerializedName("clima") val clima: String,
    @SerializedName("temporada") val temporada: String,
    @SerializedName("tipoanimal") val tipoanimal: String,
    @SerializedName("nombrecomun") val nombrecomun: String,
    @SerializedName("nombrecientifico") val nombrecientifico: String,
    // el backend lo quiere como String
    @SerializedName("numeroindividuos") val numeroindividuos: String?,
    @SerializedName("tipoobservacion") val tipoobservacion: String,
    @SerializedName("observaciones") val observaciones: String,
    @SerializedName("latitude") val latitude: Double?,
    @SerializedName("longitude") val longitude: Double?,
    @SerializedName("fecha") val fecha: String,
    @SerializedName("editado") val editado: String,
    // solo si el backend no lo inyecta vía JWT:
    @SerializedName("id_usuario") val idUsuario: Int? = null
)

fun FormularioUnoEntity.toSubmission(idUsuarioToken: Int? = null): FormularioUnoSubmission =
    FormularioUnoSubmission(
        transecto = transecto,
        clima = clima,
        temporada = temporada,
        tipoanimal = tipoAnimal,
        nombrecomun = nombreComun,
        nombrecientifico = nombreCientifico,
        numeroindividuos = numeroIndividuos?.trim()?.ifEmpty { null },
        tipoobservacion = tipoObservacion,
        observaciones = observaciones,
        latitude = latitude,
        longitude = longitude,
        fecha = fecha,
        editado = editado,
        idUsuario = idUsuarioToken
    )
