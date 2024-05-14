package com.example.swacademy_android.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReturnMultiUseRequestDto(
    @SerialName("locationName")
    val locationName: String,
    @SerialName("locationAddress")
    val locationAddress: String
)