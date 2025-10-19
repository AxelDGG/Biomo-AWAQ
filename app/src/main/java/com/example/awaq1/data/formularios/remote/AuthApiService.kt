package com.example.awaq1.data.formularios.remote

import com.example.awaq1.data.formularios.FormularioCincoEntity
import com.example.awaq1.data.formularios.FormularioCuatroEntity
import com.example.awaq1.data.formularios.FormularioDosEntity
import com.example.awaq1.data.formularios.FormularioSeisEntity
import com.example.awaq1.data.formularios.FormularioSieteEntity
import com.example.awaq1.data.formularios.FormularioTresEntity
import com.example.awaq1.data.formularios.FormularioUnoEntity
import com.example.awaq1.data.formularios.remote.dto.FormularioUnoSubmission
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


data class ApiMessage(
    val message: String?,
    val tenant: String?,
    val formKey: String?,
    val data: Any?
)

interface AuthApiService {
    //para futuras implementaciones
    @POST("api/biomo/users/login")
    suspend fun signIn(@Body request: AuthRequest): Response<AuthResponse>

    @GET("api/biomo/profile")
    suspend fun getProfile(): Response<ProfileResponse>

    //POST de los formularios
    @POST("/api/{tenant}/forms/{formKey}/submission")
    suspend fun sendFormUno(
        @Path("tenant") tenant: String,
        @Path("formKey") formKey: String,
        @Body body: FormularioUnoSubmission
    ): Response<ApiMessage>
    @POST("/api/biomo/forms/2/submission")
    suspend fun sendFormDos(@Body formularioEntities: FormularioDosEntity): Response<FormularioResponse>

    @POST("/api/biomo/forms/3/submission")
    suspend fun sendFormTres(@Body formularioEntities: FormularioTresEntity): Response<FormularioResponse>

    @POST("/api/biomo/forms/4/submission")
    suspend fun sendFormuCuatro(@Body formularioEntities: FormularioCuatroEntity): Response<FormularioResponse>

    @POST("/api/biomo/forms/5/submission")
    suspend fun sendFormCinco(@Body formularioEntities: FormularioCincoEntity): Response<FormularioResponse>

    @POST("/api/biomo/forms/6/submission")
    suspend fun sendFormSeis(@Body formularioEntities: FormularioSeisEntity): Response<FormularioResponse>

    @POST("/api/biomo/forms/7/submission")
    suspend fun sendFormSiete(@Body formularioEntities: FormularioSieteEntity): Response<FormularioResponse>

    @POST("/api/biomo/users/logout")
    suspend fun logout(): Response<Unit>

}