package com.example.swacademy_android.data.repository

import com.example.swacademy_android.data.datasource.HomeRemoteDataSource
import com.example.swacademy_android.data.model.response.HomeResponseDto
import com.example.swacademy_android.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor( private val homeRemoteDataSource : HomeRemoteDataSource) : HomeRepository {
    override suspend fun getHomeData(): Result<HomeResponseDto> {
        TODO("Not yet implemented")
    }
}