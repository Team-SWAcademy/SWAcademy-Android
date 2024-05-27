package com.example.swacademy_android.data.repository

import com.example.swacademy_android.data.datasource.MypageRemoteDataSource
import com.example.swacademy_android.data.model.response.MypageResponseDto
import com.example.swacademy_android.domain.repository.MypageRepository
import javax.inject.Inject

class MypageRepositoryImpl @Inject constructor(private val mypageRemoteDataSource:MypageRemoteDataSource) :
    MypageRepository {
    override suspend fun getMypageData(): Result<MypageResponseDto> =
        runCatching {
            mypageRemoteDataSource.getMypageData()
        }.onSuccess {

        }.onFailure {

        }
}