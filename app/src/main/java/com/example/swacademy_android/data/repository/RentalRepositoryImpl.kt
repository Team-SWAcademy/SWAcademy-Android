package com.example.swacademy_android.data.repository

import com.example.swacademy_android.data.datasource.RentalRemoteDataSource
import com.example.swacademy_android.data.model.request.RentalMultiUseRequestDto
import com.example.swacademy_android.data.model.response.RentalLocationResponseDto
import com.example.swacademy_android.data.model.response.RentalMultiUseResponseDto
import com.example.swacademy_android.domain.repository.RentalRepository
import timber.log.Timber
import javax.inject.Inject

class RentalRepositoryImpl @Inject constructor(private val rentalMultiUseDataSource: RentalRemoteDataSource) :
    RentalRepository {
    override suspend fun postRentalMultiUse(rentalMultiUseRequestDto: RentalMultiUseRequestDto): Result<RentalMultiUseResponseDto> =
        runCatching {
            rentalMultiUseDataSource.postRentalMultiUse(rentalMultiUseRequestDto)
        }.onSuccess {
            Timber.d("get home data 성공")
        }.onFailure {
            Timber.e("get home data 실패")
        }

    override suspend fun getRentalLocationData(
        locationId: Int,
        point: Int
    ): Result<RentalLocationResponseDto> = runCatching {
        rentalMultiUseDataSource.getRentalLocationData(locationId, point)
    }.onSuccess {

    }.onFailure {

    }
}