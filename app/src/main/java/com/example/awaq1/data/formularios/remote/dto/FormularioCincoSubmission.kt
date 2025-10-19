package com.example.awaq1.data.formularios.remote.dto

import com.example.awaq1.data.formularios.FormularioCincoEntity
import com.google.gson.annotations.SerializedName

data class FormularioCincoSubmission(
    @SerializedName("zona") val zona: String,
    @SerializedName("clima") val clima: String,
    @SerializedName("temporada") val temporada: String,
    @SerializedName("tipoanimal") val tipoAnimal: String,
    @SerializedName("nombrecomun") val nombreComun: String,
    @SerializedName("nombrecientifico") val nombreCientifico: String,
    @SerializedName("numeroindividuos") val numeroIndividuos: String,
    @SerializedName("tipoobservacion") val tipoObservacion: String,
    @SerializedName("alturaobservacion") val alturaObservacion: String,
    @SerializedName("observaciones") val observaciones: String,
    @SerializedName("latitude") val latitude: Double? = null,
    @SerializedName("longitude") val longitude: Double? = null,
    @SerializedName("fecha") val fecha: String,
    @SerializedName("editado") val editado: String
)

fun FormularioCincoEntity.toSubmission(): FormularioCincoSubmission =
    FormularioCincoSubmission(
        zona = zona,
        clima = clima,
        temporada = temporada,
        tipoAnimal = tipoAnimal,
        nombreComun = nombreComun,
        nombreCientifico = nombreCientifico,
        numeroIndividuos = numeroIndividuos,
        tipoObservacion = tipoObservacion,
        alturaObservacion = alturaObservacion,
        observaciones = observaciones,
        latitude = latitude,
        longitude = longitude,
        fecha = fecha,
        editado = editado
    )
