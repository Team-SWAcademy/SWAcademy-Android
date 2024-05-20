package com.example.swacademy_android.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RentalMultiUseRequestDto (
    @SerialName("locationId")
    val locationId : Int,
    @SerialName("point")
    val point:Int,
    @SerialName("multiUseContainerId")
    val multiUseContainerId : Int
)