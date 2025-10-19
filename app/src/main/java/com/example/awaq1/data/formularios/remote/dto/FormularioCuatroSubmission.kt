package com.example.awaq1.data.formularios.remote.dto

import com.google.gson.annotations.SerializedName
import com.example.awaq1.data.formularios.FormularioCuatroEntity

data class FormularioCuatroSubmission(
    @SerializedName("codigo") val codigo: String,
    @SerializedName("clima") val clima: String,
    @SerializedName("temporada") val temporada: String,
    @SerializedName("quad_a") val quadA: String,
    @SerializedName("quad_b") val quadB: String,
    @SerializedName("sub_quad") val subQuad: String,
    @SerializedName("habitodecrecimiento") val habitoDeCrecimiento: String,
    @SerializedName("nombrecomun") val nombreComun: String,
    @SerializedName("nombrecientifico") val nombreCientifico: String,
    @SerializedName("placa") val placa: String,
    @SerializedName("circunferencia") val circunferencia: String,
    @SerializedName("distancia") val distancia: String,
    @SerializedName("estatura") val estatura: String,
    @SerializedName("altura") val altura: String,
    @SerializedName("observaciones") val observaciones: String,
    @SerializedName("latitude") val latitude: Double?,
    @SerializedName("longitude") val longitude: Double?,
    @SerializedName("fecha") val fecha: String,
    @SerializedName("editado") val editado: String,
    @SerializedName("id_usuario") val idUsuario: Int? = null
)

fun FormularioCuatroEntity.toSubmission(idUsuarioToken: Int? = null): FormularioCuatroSubmission =
    FormularioCuatroSubmission(
        codigo = codigo,
        clima = clima,
        temporada = temporada,
        quadA = this.quad_a,
        quadB = this.quad_b,
        subQuad = this.sub_quad,
        habitoDeCrecimiento = habitoDeCrecimiento,
        nombreComun = nombreComun,
        nombreCientifico = nombreCientifico,
        placa = placa,
        circunferencia = circunferencia,
        distancia = distancia,
        estatura = estatura,
        altura = altura,
        observaciones = observaciones,
        latitude = latitude,
        longitude = longitude,
        fecha = fecha,
        editado = editado,
        idUsuario = idUsuarioToken
    )
