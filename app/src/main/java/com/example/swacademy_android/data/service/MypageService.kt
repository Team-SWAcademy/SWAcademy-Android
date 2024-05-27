package com.example.swacademy_android.data.service

import com.example.swacademy_android.data.model.response.MypageResponseDto
import retrofit2.http.GET

interface MypageService {
    @GET("/api/v1/users/my-page")
    suspend fun getMypageData() : MypageResponseDto
}