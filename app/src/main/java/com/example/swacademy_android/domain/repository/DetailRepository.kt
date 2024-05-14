package com.example.swacademy_android.domain.repository

import com.example.swacademy_android.data.model.response.DetailMultiUseResponseDto

interface DetailRepository {
    suspend fun getDetailData(useAt : String) : Result<DetailMultiUseResponseDto>
}