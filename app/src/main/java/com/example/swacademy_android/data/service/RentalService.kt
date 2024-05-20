package com.example.swacademy_android.data.service

import com.example.swacademy_android.data.model.request.RentalMultiUseRequestDto
import com.example.swacademy_android.data.model.response.DetailMultiUseResponseDto
import com.example.swacademy_android.data.model.response.HomeResponseDto
import com.example.swacademy_android.data.model.response.RentalLocationResponseDto
import com.example.swacademy_android.data.model.response.RentalMultiUseResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RentalService {
    @POST("/api/v1/uses")
    suspend fun postRentalMultiUse(@Body rentalMultiUseRequestDto: RentalMultiUseRequestDto) : RentalMultiUseResponseDto

    @GET("/api/v1/uses/location")
    suspend fun getRentalLocationData(@Query("locationId") locationId:Int, @Query("point") point:Int) : RentalLocationResponseDto
}