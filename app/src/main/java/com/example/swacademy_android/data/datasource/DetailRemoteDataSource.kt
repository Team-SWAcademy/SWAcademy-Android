package com.example.swacademy_android.data.datasource

import com.example.swacademy_android.data.model.request.ReturnMultiUseRequestDto
import com.example.swacademy_android.data.service.DetailService
import com.example.swacademy_android.data.service.HomeService
import javax.inject.Inject

class DetailRemoteDataSource @Inject constructor(private val detailService: DetailService) {
    suspend fun getDetailData(useAt: String) = detailService.getDetailData(useAt)
    suspend fun patchReturnMultiUse(useAt: String, locationId: ReturnMultiUseRequestDto) =
        detailService.patchReturnMultiUse(useAt,locationId)
}