package com.example.swacademy_android.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RentalMultiUseRequestDto (
    @SerialName("locationName")
    val locationName: String,
    @SerialName("locationAddress")
    val locationAddress: String,
    @SerialName("point")
    val point:Int,
    @SerialName("multiUseContainerId")
    val multiUseContainerId : Int
)