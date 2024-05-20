package com.example.swacademy_android.data.service

import com.example.swacademy_android.data.model.request.ReturnMultiUseRequestDto
import com.example.swacademy_android.data.model.response.DetailMultiUseResponseDto
import com.example.swacademy_android.data.model.response.ReturnMultiUseResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface DetailService {
    @GET("/api/v1/uses/{useAt}")
    suspend fun getDetailData(@Path("useAt") useAt: String): DetailMultiUseResponseDto

    @PATCH("/api/v1/uses/{useAt}")
    suspend fun patchReturnMultiUse(
        @Path("useAt") useAt: String,
        @Body locationId: ReturnMultiUseRequestDto
    ): ReturnMultiUseResponseDto
}