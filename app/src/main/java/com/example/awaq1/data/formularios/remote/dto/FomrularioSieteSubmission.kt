package com.example.awaq1.data.formularios.remote.dto

import com.example.awaq1.data.formularios.FormularioSieteEntity
import com.google.gson.annotations.SerializedName

data class FormularioSieteSubmission(
    @SerializedName("clima") val clima: String,
    @SerializedName("temporada") val temporada: String,
    @SerializedName("zona") val zona: String,
    @SerializedName("pluviosidad") val pluviosidad: String,
    @SerializedName("temperaturamaxima") val temperaturaMaxima: String,
    @SerializedName("humedadmaxima") val humedadMaxima: String,
    @SerializedName("temperaturaminima") val temperaturaMinima: String,
    @SerializedName("nivelquebrada") val nivelQuebrada: String,
    @SerializedName("latitude") val latitude: Double? = null,
    @SerializedName("longitude") val longitude: Double? = null,
    @SerializedName("fecha") val fecha: String,
    @SerializedName("editado") val editado: String,
    @SerializedName("id_usuario") val idUsuario: Int? = null
)

fun FormularioSieteEntity.toSubmission(idUsuarioToken: Int? = null): FormularioSieteSubmission =
    FormularioSieteSubmission(
        clima = clima,
        temporada = temporada,
        zona = zona,
        pluviosidad = pluviosidad,
        temperaturaMaxima = temperaturaMaxima,
        humedadMaxima = humedadMaxima,
        temperaturaMinima = temperaturaMinima,
        nivelQuebrada = nivelQuebrada,
        latitude = latitude,
        longitude = longitude,
        fecha = fecha,
        editado = editado,
        idUsuario = idUsuarioToken
    )
