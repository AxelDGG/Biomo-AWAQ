package com.example.awaq1.data.formularios.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

// Cuerpo genérico para cualquier formulario (clave→valor)
typealias FormBody = Map<String, @JvmSuppressWildcards Any?>

data class ApiMessage(
    val message: String?,
    val tenant: String?,
    val formKey: String?,
    val data: Any?
)

interface AuthApiService {

    // ---- Auth / Perfil ----
    @POST("api/biomo/users/login")
    suspend fun signIn(@Body request: AuthRequest): Response<AuthResponse>

    @GET("api/biomo/profile")
    suspend fun getProfile(): Response<ProfileResponse>

    @POST("api/biomo/users/logout")
    suspend fun logout(): Response<Unit>

    // ---- Envío de formularios (1–7) con la misma firma ----
    // Cambia solo formKey = "1".."7"
    @POST("api/{tenant}/forms/{formKey}/submission")
    suspend fun sendForm(
        @Path("tenant") tenant: String,
        @Path("formKey") formKey: String,
        @Body body: FormSubmissionRequest
    ): Response<ApiMessage>
}
