package com.example.swacademy_android.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class MypageResponseDto(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val result: Result
) {
    @Serializable
    data class Result(
        val nickname: String,
        val gender: Boolean,
        val currentPoint: Long,
        val totalUseCount: Long,
        val totalReturnCount: Long,
        val dailyStatisticsResList: List<DailyStatisticsResList>,
        val monthlyStatisticsResList: List<MonthlyStatisticsResList>,
    ) {
        @Serializable
        data class DailyStatisticsResList(
            val dayOfWeek: Long,
            val useCount: Long,
            val returnCount: Long,
        )

        @Serializable
        data class MonthlyStatisticsResList(
            val month: Long,
            val useCount: Long,
            val returnCount: Long,
        )
    }
}
