package com.example.swacademy_android.data.datasource

import com.example.swacademy_android.data.model.request.RentalMultiUseRequestDto
import com.example.swacademy_android.data.service.HomeService
import com.example.swacademy_android.data.service.RentalService
import retrofit2.http.Body
import javax.inject.Inject

class RentalRemoteDataSource @Inject constructor(private val rentalService: RentalService) {
    suspend fun postRentalMultiUse(rentalMultiUseRequestDto: RentalMultiUseRequestDto) = rentalService.postRentalMultiUse(rentalMultiUseRequestDto)
    suspend fun getRentalLocationData(locationId:Int, point:Int) = rentalService.getRentalLocationData(locationId, point)
}