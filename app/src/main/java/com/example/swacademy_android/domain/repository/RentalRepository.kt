package com.example.swacademy_android.domain.repository

import com.example.swacademy_android.data.model.request.RentalMultiUseRequestDto
import com.example.swacademy_android.data.model.response.HomeResponseDto
import com.example.swacademy_android.data.model.response.RentalMultiUseResponseDto

interface RentalRepository {
    suspend fun postRentalMultiUse(rentalMultiUseRequestDto: RentalMultiUseRequestDto): Result<RentalMultiUseResponseDto>

}