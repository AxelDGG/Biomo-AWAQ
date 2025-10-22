package com.example.awaq1.data.formularios.remote

import com.example.awaq1.data.formularios.*
import com.example.awaq1.data.formularios.remote.dto.toSubmission
import com.example.awaq1.data.formularios.local.RegistroEnvioRepository
import retrofit2.HttpException
import java.io.IOException

class FormsRemoteRepository(
    private val authApiService: AuthApiService,
    private val registroEnvioRepository: RegistroEnvioRepository   // 👈 NUEVO
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

    /**
     * Envía cualquier formulario (1..7) y, si el backend regresa HTTP 201, marca como enviado
     * en la tabla RegistroEnvio (formType, formId).
     */
    private suspend fun enviar(
        formType: Int,
        formId: Long,
        body: Map<String, @JvmSuppressWildcards Any?>
    ): Result<Unit> = runCatching {
        val resp = authApiService.sendForm(
            tenant = "biomo",
            formKey = formType.toString(),
            body = body
        )
        if (resp.isSuccessful) {
            // ✅ Solo marcamos como enviado si el código es exactamente 201
            if (resp.code() == 201) {
                registroEnvioRepository.markSent(formType, formId)
            }
        } else {
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
        return enviar(
            formType = 1,
            formId = form.id,            // 👈 ajusta si tu campo no se llama 'id'
            body = payload
        )
    }

    suspend fun enviarFormularioDos(form: FormularioDosEntity, userIdDelToken: Int?): Result<Unit> {
        val body = form.toSubmission(userIdDelToken)
        return enviar(2, form.id, body) // 👈 ajusta 'form.id' si es necesario
    }

    suspend fun enviarFormularioTres(form: FormularioTresEntity, userIdDelToken: Int?): Result<Unit> {
        val body = form.toSubmission(userIdDelToken)
        return enviar(3, form.id, body)
    }

    suspend fun enviarFormularioCuatro(form: FormularioCuatroEntity, userIdDelToken: Int?): Result<Unit> {
        val body = form.toSubmission(userIdDelToken)
        return enviar(4, form.id, body)
    }

    suspend fun enviarFormularioCinco(form: FormularioCincoEntity, userIdDelToken: Int?): Result<Unit> {
        val body = form.toSubmission(userIdDelToken)
        return enviar(5, form.id, body)
    }

    suspend fun enviarFormularioSeis(form: FormularioSeisEntity, userIdDelToken: Int?): Result<Unit> {
        val body = form.toSubmission(userIdDelToken)
        return enviar(6, form.id, body)
    }

    suspend fun enviarFormularioSiete(form: FormularioSieteEntity, userIdDelToken: Int?): Result<Unit> {
        val body = form.toSubmission(userIdDelToken)
        return enviar(7, form.id, body)
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
