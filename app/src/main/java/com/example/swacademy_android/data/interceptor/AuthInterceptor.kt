package com.example.swacademy_android.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = "Bearer "

        val originalRequest = chain.request()

        val headerRequest = originalRequest.newBuilder()
            .header(
                "Authorization",
                "$token"
            )
            .build()
        return chain.proceed(headerRequest)
    }
}