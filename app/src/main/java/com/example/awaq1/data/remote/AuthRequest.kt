package com.example.awaq1.data.remote

import retrofit2.http.Header

data class AuthRequest(
    val user_email: String,
    val password: String,
)

data class AuthResponse(
    val message: String,
    val token: String
)

data class ProfileResponse(
    val message: String,
    val user: User
)

data class User(
    val id: Int,
    val user_email: String
)
