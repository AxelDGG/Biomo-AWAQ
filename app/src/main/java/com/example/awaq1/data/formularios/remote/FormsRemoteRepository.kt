package com.example.awaq1.data.formularios.remote

import com.example.awaq1.data.formularios.*
import com.example.awaq1.data.formularios.remote.AuthApiService
import retrofit2.HttpException
import java.io.IOException
import com.example.awaq1.data.formularios.remote.dto.toSubmission


class FormsRemoteRepository(
    private val authApiService: AuthApiService
) {

    // metodos internos que usan safeApiCall y lanzan excepciones
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

    // Ejemplo de métodos internos que pueden lanzar excepción

    suspend fun sendFormDos(form: FormularioDosEntity) = safeApiCall {
        authApiService.sendFormDos(form)
    }

    suspend fun enviarFormularioUno(
        form: FormularioUnoEntity,
        userIdDelToken: Int? = null // pásalo solo si el backend lo exige
    ): Result<Unit> = runCatching {
        val payload = form.toSubmission(userIdDelToken)
        val resp = authApiService.sendFormUno(
            tenant = "biomo",
            formKey = "1",
            body = payload
        )
        if (!resp.isSuccessful) {
            val text = resp.errorBody()?.string()
            throw IllegalStateException("HTTP ${resp.code()} ${resp.message()} :: ${text ?: "sin detalle"}")
        }
    }

    suspend fun sendFormUno(form: FormularioUnoEntity, userIdDelToken: Int? = null) =
        enviarFormularioUno(form, userIdDelToken)

    suspend fun enviarFormularioDos(form: FormularioDosEntity): Result<Unit> {
        return try {
            sendFormDos(form)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ... otros métodos públicos similares para otros formularios ...

    // Example logout method using safeApiCall
    suspend fun logout() {
        try {
            val response = authApiService.logout()
            if (!response.isSuccessful) {
                throw HttpException(response)
            }
        } catch (e: IOException) {
            throw Exception("Error de red o sin conexión", e)
        } catch (e: HttpException) {
            throw Exception("Error HTTP ${e.code()}: ${e.message()}", e)
        }
    }

    // Métodos de autenticación si los usas dentro del flujo de formularios
    suspend fun signIn(request: AuthRequest) = safeApiCall {
        authApiService.signIn(request)
    }

    suspend fun getProfile() = safeApiCall {
        authApiService.getProfile()
    }
}

