package com.example.swacademy_android.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhY2FkZW15QGFjYWRlbXkuY29tIiwic3ViIjoiMzI1MTEzMTQxMSIsImlhdCI6MTcxNTE3Mzk3NCwiZXhwIjoxNzE1MjYwMzc0fQ.pp_hBCGXUAfLBVy7gx2UbSjNhTXkiHmtRiaTXz-r-Pk"

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