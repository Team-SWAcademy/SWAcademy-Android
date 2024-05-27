package com.example.swacademy_android.domain.repository

import com.example.swacademy_android.data.model.response.MypageResponseDto

interface MypageRepository {
    suspend fun getMypageData(): Result<MypageResponseDto>
}