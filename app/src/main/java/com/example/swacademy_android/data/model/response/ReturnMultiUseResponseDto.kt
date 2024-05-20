package com.example.swacademy_android.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ReturnMultiUseResponseDto(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val result: Result
) {
    @Serializable
    data class Result(
        val returnLocationId: Long,
        val returnLocationName: String,
        val returnLocationAddress: String,
        val returnTime: String,
        val currentPoint: Long,
        val acquiredPoint: Long,
        val userId: Long,
        val status: String,
    )
}
