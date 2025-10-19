package com.example.awaq1.data.formularios.remote

import com.example.awaq1.data.formularios.*
import com.example.awaq1.data.formularios.remote.dto.toSubmission
import retrofit2.HttpException
import java.io.IOException

class FormsRemoteRepository(
    private val authApiService: AuthApiService
) {

    // --- Helper genérico para llamadas Retrofit que devuelven Response<T> y lanzan errores claros
    private suspend fun <T> safeApiCall(apiCall: suspend () -> retrofit2.Response<T>): T {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                response.body() ?: throw Exception("Respuesta vacía del servidor")
            } else {
                throw HttpException(response)
            }
        } catch (e: IOException) {
            throw Exception("Error de red o sin conexión", e)
        } catch (e: HttpException) {
            throw Exception("Error HTTP ${e.code()}: ${e.message()}", e)
        }
    }

    // --- Helper para enviar cualquier formulario (1..7) con el mismo endpoint
    private suspend fun enviar(formKey: String, body: Map<String, @JvmSuppressWildcards Any?>): Result<Unit> =
        runCatching {
            val resp = authApiService.sendForm(
                tenant = "biomo",
                formKey = formKey,
                body = body
            )
            if (!resp.isSuccessful) {
                val text = resp.errorBody()?.string()
                throw IllegalStateException("HTTP ${resp.code()} ${resp.message()} :: ${text ?: "sin detalle"}")
            }
        }

    // -----------------------
    // Envíos por formulario
    // -----------------------

    suspend fun enviarFormularioUno(
        form: FormularioUnoEntity,
        userIdDelToken: Int? = null
    ): Result<Unit> {
        val payload = form.toSubmission(userIdDelToken)
        return enviar("1", payload)
    }

    suspend fun enviarFormularioDos(form: FormularioDosEntity, userIdDelToken: Int?): Result<Unit> {
        val body = form.toSubmission(userIdDelToken)
        return enviar("2", body)
    }

    suspend fun enviarFormularioTres(form: FormularioTresEntity, userIdDelToken: Int?): Result<Unit> {
        val body = form.toSubmission(userIdDelToken)
        return enviar("3", body)
    }

    suspend fun enviarFormularioCuatro(form: FormularioCuatroEntity, userIdDelToken: Int?): Result<Unit> {
        val body = form.toSubmission(userIdDelToken)
        return enviar("4", body)
    }

    suspend fun enviarFormularioCinco(form: FormularioCincoEntity, userIdDelToken: Int?): Result<Unit> {
        val body = form.toSubmission(userIdDelToken)
        return enviar("5", body)
    }

    suspend fun enviarFormularioSeis(form: FormularioSeisEntity, userIdDelToken: Int?): Result<Unit> {
        val body = form.toSubmission(userIdDelToken)
        return enviar("6", body)
    }

    suspend fun enviarFormularioSiete(form: FormularioSieteEntity, userIdDelToken: Int?): Result<Unit> {
        val body = form.toSubmission(userIdDelToken)
        return enviar("7", body)
    }

    // -----------------------
    // Auth (si los usas aquí)
    // -----------------------

    suspend fun signIn(request: AuthRequest) = safeApiCall {
        authApiService.signIn(request)
    }

    suspend fun getProfile() = safeApiCall {
        authApiService.getProfile()
    }

    suspend fun logout() {
        try {
            val response = authApiService.logout()
            if (!response.isSuccessful) throw HttpException(response)
        } catch (e: IOException) {
            throw Exception("Error de red o sin conexión", e)
        } catch (e: HttpException) {
            throw Exception("Error HTTP ${e.code()}: ${e.message()}", e)
        }
    }
}
