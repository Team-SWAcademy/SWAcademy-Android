package com.example.swacademy_android.data.datasource

import com.example.swacademy_android.data.service.HomeService
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor( private val homeService : HomeService) {

    suspend fun getHomeData() = homeService.getHomeData()
}