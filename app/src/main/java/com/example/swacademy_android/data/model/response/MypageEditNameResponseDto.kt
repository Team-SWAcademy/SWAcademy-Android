package com.example.swacademy_android.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class MypageEditNameResponseDto(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val result: Result
){
    @Serializable
    data class Result(
        val editNickname: String?,
    )
}