package com.example.swacademy_android.domain.repository

import com.example.swacademy_android.data.model.response.HomeResponseDto

interface HomeRepository {

    suspend fun getHomeData(): Result<HomeResponseDto>
}