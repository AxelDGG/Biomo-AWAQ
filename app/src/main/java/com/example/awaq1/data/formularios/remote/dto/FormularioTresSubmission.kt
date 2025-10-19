package com.example.awaq1.data.formularios.remote.dto



import com.google.gson.annotations.SerializedName
import com.example.awaq1.data.formularios.FormularioTresEntity

data class FormularioTresSubmission(
    @SerializedName("codigo") val codigo: String,
    @SerializedName("clima") val clima: String,
    @SerializedName("temporada") val temporada: String,
    @SerializedName("seguimiento") val seguimiento: Boolean,
    @SerializedName("cambio") val cambio: Boolean,
    @SerializedName("cobertura") val cobertura: String,
    @SerializedName("tipocultivo") val tipocultivo: String,
    @SerializedName("disturbio") val disturbio: String,
    @SerializedName("observaciones") val observaciones: String,
    @SerializedName("latitude") val latitude: Double?,
    @SerializedName("longitude") val longitude: Double?,
    @SerializedName("fecha") val fecha: String,
    @SerializedName("editado") val editado: String,
    @SerializedName("id_usuario") val idUsuario: Int? = null // 👈 backend lo requiere (puede venir del token)
)

fun FormularioTresEntity.toSubmission(idUsuarioToken: Int? = null): FormularioTresSubmission =
    FormularioTresSubmission(
        codigo = codigo,
        clima = clima,
        temporada = temporada,
        seguimiento = seguimiento,
        cambio = cambio,
        cobertura = cobertura,
        tipocultivo = tipoCultivo, // 👈 igual al nombre esperado por backend
        disturbio = disturbio,
        observaciones = observaciones,
        latitude = latitude,
        longitude = longitude,
        fecha = fecha,
        editado = editado,
        idUsuario = idUsuarioToken
    )
