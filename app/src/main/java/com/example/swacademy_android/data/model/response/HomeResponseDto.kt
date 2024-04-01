package com.example.swacademy_android.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeResponseDto(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: HomeData
) {
    @Serializable
    data class HomeData(
        @SerialName("name")
        val name: String?,
        @SerialName("point")
        val point: Int?
    )
}
