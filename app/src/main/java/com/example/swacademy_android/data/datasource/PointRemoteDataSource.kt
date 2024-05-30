package com.example.swacademy_android.data.datasource

import com.example.swacademy_android.data.model.response.PointListResponseDto
import com.example.swacademy_android.data.service.PointService
import javax.inject.Inject

class PointRemoteDataSource @Inject constructor(private val pointService: PointService) {
    suspend fun getPointListData(page:Int, size: Int) = pointService.getPointListData(page, size)
}