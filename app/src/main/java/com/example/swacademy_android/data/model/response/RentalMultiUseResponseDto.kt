package com.example.swacademy_android.data.model.response

import com.example.swacademy_android.data.model.request.RentalMultiUseRequestDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RentalMultiUseResponseDto(
    @SerialName("isSuccess")
    val isSuccess: Boolean,
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String,
    @SerialName("result")
    val result: Result
) {
    @Serializable
    data class Result(
        @SerialName("useAt")
        val useAt: String,
        @SerialName("point")
        val point: Int,
        @SerialName("userId")
        val userId: Int,
        @SerialName("locationId")
        val locationId: Int,
        @SerialName("locationName")
        val locationName: String,
        @SerialName("locationAddress")
        val locationAddress: String,
        @SerialName("locationImageUrl")
        val locationImageUrl: String,
        @SerialName("latitude")
        val latitude: Double,
        @SerialName("longitude")
        val longitude: Double,
        @SerialName("multiUseContainerId")
        val multiUseContainerId: Int,
        @SerialName("status")
        val status: String

    )
}
