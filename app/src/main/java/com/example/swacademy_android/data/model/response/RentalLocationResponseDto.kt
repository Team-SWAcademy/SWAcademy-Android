package com.example.swacademy_android.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class RentalLocationResponseDto(
    @SerialName("isSuccess")
    val isSuccess: Boolean,
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String,
    @SerialName("result")
    val result: Result,
) {
    @Serializable
    data class Result(
        @SerialName("locationId")
        val locationId: Long,
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
        @SerialName("point")
        val point: Long,
        @SerialName("multiUseContainerIdList")
        val multiUseContainerIdList: List<Long>,
    )
}