package com.example.awaq1.data.formularios.remote

import com.example.awaq1.data.formularios.local.TokenManager
import okhttp3.Dns
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Inet4Address
import java.net.InetAddress
import java.util.concurrent.TimeUnit

object RetroFitClient {
    private const val BASE_URL = "https://ekgcss8ww8o4ok480g08soo4.91.98.193.75.sslip.io/"

    fun create(tokenManager: TokenManager): AuthApiService {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // DNS Personalizado para forzar el uso de IPv4
        val ipv4Dns = object : Dns {
            override fun lookup(hostname: String): List<InetAddress> {
                return InetAddress.getAllByName(hostname).filter { it is Inet4Address }
            }
        }

        val client = OkHttpClient.Builder()
            .dns(ipv4Dns) // Se aplica el DNS personalizado
            .addInterceptor(ApiKeyInterceptor())
            .addInterceptor(AuthInterceptor(tokenManager))
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS) // Aumentado el tiempo de espera
            .readTimeout(30, TimeUnit.SECONDS)    // Aumentado el tiempo de espera
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(AuthApiService::class.java)
    }
}
