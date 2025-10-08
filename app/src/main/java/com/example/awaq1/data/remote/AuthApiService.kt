package com.example.awaq1.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApiService {
    @POST("api/biomo/register")
    suspend fun signUp(@Header ("biomo-key-456") contentType: String, @Body request: AuthRequest): Response<Unit>

    @POST("api/biomo/login")
    suspend fun signIn(@Body request: AuthRequest): Response<AuthResponse>

    @GET("api/profile")
    suspend fun getProfile(): Response<ProfileResponse>

    @POST("api/todos") // o la ruta que corresponda en tu backend
    suspend fun submitForm(@Body formRequest: FormRequest): Response<Unit> // Asumimos que el backend no devuelve nada en el cuerpo

}