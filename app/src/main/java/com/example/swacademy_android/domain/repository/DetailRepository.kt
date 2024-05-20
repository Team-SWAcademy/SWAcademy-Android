package com.example.swacademy_android.domain.repository

import com.example.swacademy_android.data.model.request.ReturnMultiUseRequestDto
import com.example.swacademy_android.data.model.response.DetailMultiUseResponseDto
import com.example.swacademy_android.data.model.response.ReturnMultiUseResponseDto

interface DetailRepository {
    suspend fun getDetailData(useAt: String): Result<DetailMultiUseResponseDto>
    suspend fun patchReturnMultiUse(
        useAt: String,
        locationId: ReturnMultiUseRequestDto
    ): Result<ReturnMultiUseResponseDto>

}