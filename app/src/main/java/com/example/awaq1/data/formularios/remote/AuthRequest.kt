package com.example.awaq1.data.formularios.remote

import com.google.gson.annotations.SerializedName


data class AuthRequest(
    @SerializedName("user_email") val email: String,
    @SerializedName("password") val password: String
)


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


data class ProfileResponse(
    @SerializedName("message") val message: String,
    @SerializedName("user") val user: User
)


data class User(
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String,
    @SerializedName("user_email") val userEmail: String,
    @SerializedName("lastAccess") val lastAccess: String,
    @SerializedName("lastLogin") val lastLogin: String,
    @SerializedName("tenant") val tenant: String
)