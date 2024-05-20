package com.example.swacademy_android.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class DetailMultiUseResponseDto(
    @SerialName("isSuccess")
    val isSuccess: Boolean,
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String,
    @SerialName("result")
    val result: DetailResult
) {
    @Serializable
    data class DetailResult(
        @SerialName("rentalLocationId")
        val rentalLocationId: Int,
        @SerialName("locationImageUrl")
        val locationImageUrl: String,
        @SerialName("locationName")
        val locationName: String,
        @SerialName("locationAddress")
        val locationAddress: String,
        @SerialName("useAt")
        val useAt: String,
        @SerialName("point")
        val point: Int,
        @SerialName("multiUseContainerId")
        val multiUseContainerId: Int,
        @SerialName("getReturnResList")
        val getReturnResList: List<ReturnRes>,
    ) {
        @Serializable
        data class ReturnRes(
            @SerialName("locationId")
            val locationId: Int,
            @SerialName("name")
            val name: String,
            @SerialName("address")
            val address: String,
            @SerialName("latitude")
            val latitude: Double,
            @SerialName("longitude")
            val longitude: Double,
            @SerialName("locationType")
            val locationType: String,
            @SerialName("imageUrl")
            val imageUrl: String
        )
    }
}