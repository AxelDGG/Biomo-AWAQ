package com.example.awaq1.data.formularios.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApiService {
    //para futuras implementaciones
  //  @POST("api/biomo/auth/signup")
  //  suspend fun signUp(@Body request: AuthRequest): Response<Unit>

    @POST("api/biomo/users/login")
    suspend fun signIn(@Body request: AuthRequest): Response<AuthResponse>

    @GET("api/biomo/profile")
    suspend fun getProfile(): Response<ProfileResponse>

    @POST("api/biomo/todos") // o la ruta que corresponda en tu backend
    suspend fun submitForm(@Body formRequest: FormRequest): Response<Unit> // Asumimos que el backend no devuelve nada en el cuerpo

    @GET("api/biomo/todos")
    suspend fun getTodos(): Response<List<TodoDto>>
}