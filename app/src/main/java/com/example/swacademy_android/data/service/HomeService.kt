package com.example.swacademy_android.data.service

import com.example.swacademy_android.data.model.response.HomeResponseDto
import retrofit2.http.GET

interface HomeService {

    @GET("/home")
    suspend fun getHomeData() : HomeResponseDto


}