package com.example.swacademy_android.data.repository

import com.example.swacademy_android.data.datasource.HomeRemoteDataSource
import com.example.swacademy_android.data.model.response.HomeResponseDto
import com.example.swacademy_android.domain.repository.HomeRepository
import timber.log.Timber
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val homeRemoteDataSource: HomeRemoteDataSource) :
    HomeRepository {
    override suspend fun getHomeData(): Result<HomeResponseDto> =
        runCatching {
            homeRemoteDataSource.getHomeData()
        }.onSuccess {
            Timber.d("get home data 성공")
        }.onFailure {
            Timber.e("get home data 실패")
        }
}