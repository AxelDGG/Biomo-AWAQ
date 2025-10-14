package com.example.awaq1.data.formularios.remote

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("apikey", "biomo-key-456")
            .addHeader("accept", "application/json")
            .build()
        return chain.proceed(newRequest)
    }
}