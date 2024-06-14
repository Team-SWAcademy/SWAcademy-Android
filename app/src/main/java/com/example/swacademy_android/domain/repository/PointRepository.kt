package com.example.swacademy_android.domain.repository

import com.example.swacademy_android.data.model.response.PointListResponseDto

interface PointRepository {
    suspend fun getPointListData(page:Int) : Result<PointListResponseDto>
}