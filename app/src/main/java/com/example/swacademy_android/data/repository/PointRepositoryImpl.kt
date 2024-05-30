package com.example.swacademy_android.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.swacademy_android.data.datasource.PointPagingSource
import com.example.swacademy_android.data.datasource.PointRemoteDataSource
import com.example.swacademy_android.data.model.response.PointListResponseDto
import com.example.swacademy_android.data.service.PointService
import com.example.swacademy_android.domain.repository.PointRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PointRepositoryImpl @Inject constructor(private val pointRemoteDataSource: PointRemoteDataSource) :
    PointRepository {
    override suspend fun getPointListData(page: Int): Result<PointListResponseDto> =
        runCatching {
            pointRemoteDataSource.getPointListData(page, 10)
        }.onSuccess {

        }.onFailure {

        }
}