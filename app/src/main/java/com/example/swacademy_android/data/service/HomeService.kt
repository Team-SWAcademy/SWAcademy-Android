package com.example.swacademy_android.data.service

import com.example.swacademy_android.data.model.response.HomeResponseDto
import retrofit2.http.GET

interface HomeService {

    @GET("/api/v1/uses")
    suspend fun getHomeData() : HomeResponseDto


}