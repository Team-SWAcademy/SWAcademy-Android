package com.example.swacademy_android.data.repository

import com.example.swacademy_android.data.datasource.DetailRemoteDataSource
import com.example.swacademy_android.data.model.request.ReturnMultiUseRequestDto
import com.example.swacademy_android.data.model.response.DetailMultiUseResponseDto
import com.example.swacademy_android.data.model.response.ReturnMultiUseResponseDto
import com.example.swacademy_android.domain.repository.DetailRepository
import com.example.swacademy_android.domain.repository.HomeRepository
import timber.log.Timber
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(private val detailRemoteDataSource: DetailRemoteDataSource) :
    DetailRepository {
    override suspend fun getDetailData(useAt: String): Result<DetailMultiUseResponseDto> =
        runCatching {
            detailRemoteDataSource.getDetailData(useAt)
        }.onSuccess {

        }.onFailure {
        }

    override suspend fun patchReturnMultiUse(
        useAt: String,
        locationId: ReturnMultiUseRequestDto
    ): Result<ReturnMultiUseResponseDto> =
        runCatching {
            detailRemoteDataSource.patchReturnMultiUse(useAt, locationId)
        }.onSuccess {

        }.onFailure {

            }
}