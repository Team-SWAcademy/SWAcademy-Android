package com.example.swacademy_android.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListResponseDto(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: ListData
) {
    @Serializable
    data class ListData(
        @SerialName("idx")
        val idx: Int?,
        @SerialName("cafe_name")
        val cafeName: String?,
        @SerialName("rental_time")
        val rentalTime: String?,
        @SerialName("cafe_thumbnail")
        val cafeThumbnail: String?
    )
}
