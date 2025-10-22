package com.example.awaq1.data.formularios.remote

import com.google.gson.annotations.SerializedName

/**
 * Representa el cuerpo de la petición para iniciar sesión.
 */
data class AuthRequest(
    @SerializedName("user_email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("metadata") val metadata: Map<String, @JvmSuppressWildcards Any>
)

/**
 * Representa el cuerpo de la petición para el envío de formularios.
 */
data class FormSubmissionRequest(
    @SerializedName("data") val data: Map<String, @JvmSuppressWildcards Any?>,
    @SerializedName("metadata") val metadata: Map<String, @JvmSuppressWildcards Any>
)

/**
 * Representa la respuesta COMPLETA del endpoint de login.
 * Esta es la versión corregida que incluye el objeto User.
 */
data class AuthResponse(
    @SerializedName("message") val message: String,
    @SerializedName("token") val token: String,
    @SerializedName("token_type") val tokenType: String,
    @SerializedName("expires_in") val expiresIn: String,
    @SerializedName("user") val user: User? // El objeto de usuario que faltaba
)

data class FormularioResponse(
    @SerializedName("message") val message: String,
)

/**
 * Respuesta del endpoint de OBTENER PERFIL.
 */
data class ProfileResponse(
    @SerializedName("message") val message: String,
    @SerializedName("user") val user: User
)

/**
 * Representa el objeto de usuario anidado en la respuesta.
 * Esta es la versión corregida que coincide con los datos de tu API.
 */
data class User(
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String,
    @SerializedName("user_email") val userEmail: String,
    @SerializedName("lastAccess") val lastAccess: String,
    @SerializedName("lastLogin") val lastLogin: String,
    @SerializedName("tenant") val tenant: String
)