package com.example.swacademy_android.data.service

import com.example.swacademy_android.data.model.response.DetailMultiUseResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailService {
    @GET("/api/v1/uses/{useAt}")
    suspend fun getDetailData(@Path("useAt") useAt:String): DetailMultiUseResponseDto
}