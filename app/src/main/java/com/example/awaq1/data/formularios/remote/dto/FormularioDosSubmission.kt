package com.example.awaq1.data.formularios.remote.dto

import com.google.gson.annotations.SerializedName
import com.example.awaq1.data.formularios.FormularioDosEntity


data class FormularioDosSubmission(
    @SerializedName("zona") val zona: String,
    @SerializedName("clima") val clima: String,
    @SerializedName("temporada") val temporada: String,
    @SerializedName("tipoanimal") val tipoanimal: String,
    @SerializedName("nombrecomun") val nombrecomun: String,
    @SerializedName("nombrecientifico") val nombrecientifico: String,
    @SerializedName("numeroindividuos") val numeroindividuos: String?, // 👈 string como en backend
    @SerializedName("tipoobservacion") val tipoobservacion: String,
    @SerializedName("alturaobservacion") val alturaobservacion: String,
    @SerializedName("observaciones") val observaciones: String,
    @SerializedName("latitude") val latitude: Double?,
    @SerializedName("longitude") val longitude: Double?,
    @SerializedName("fecha") val fecha: String,
    @SerializedName("editado") val editado: String,
    @SerializedName("id_usuario") val idUsuario: Int? = null // 👈 obligatorio (backend NOT NULL)
)

fun FormularioDosEntity.toSubmission(idUsuarioToken: Int? = null): FormularioDosSubmission =
    FormularioDosSubmission(
        zona = zona,
        clima = clima,
        temporada = temporada,
        tipoanimal = tipoAnimal,
        nombrecomun = nombreComun,
        nombrecientifico = nombreCientifico,
        numeroindividuos = numeroIndividuos?.trim()?.ifEmpty { null },
        tipoobservacion = tipoObservacion,
        alturaobservacion = alturaObservacion,
        observaciones = observaciones,
        latitude = latitude,
        longitude = longitude,
        fecha = fecha,
        editado = editado,
        idUsuario = idUsuarioToken
    )
