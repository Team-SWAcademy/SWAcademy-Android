package com.example.swacademy_android.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeResponseDto(
    @SerialName("isSuccess")
    val isSuccess: Boolean,
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String,
    @SerialName("result")
    val result: HomeResult
) {
    @Serializable
    data class HomeResult(
        @SerialName("userId")
        val userId: Int,
        @SerialName("nickname")
        val nickname: String,
        @SerialName("getUseResList")
        val getUseResList: List<UseRes>,
        @SerialName("useCount")
        val useCount: Int

    ) {
        @Serializable
        data class UseRes(
            @SerialName("rentalLocationId") val rentalLocationId: Int,
            @SerialName("locationImageUrl") val locationImageUrl: String,
            @SerialName("locationName") val locationName: String,
            @SerialName("useAt") val useAt: String,
            @SerialName("status") val status: String
        )
    }
}
