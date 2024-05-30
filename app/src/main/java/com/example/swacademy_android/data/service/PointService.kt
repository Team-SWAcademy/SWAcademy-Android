package com.example.swacademy_android.data.service

import com.example.swacademy_android.data.model.response.PointListResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PointService {
    @GET("/api/v1/points")
    suspend fun getPointListData(@Query("page") page:Int, @Query("size") size:Int) : PointListResponseDto
}