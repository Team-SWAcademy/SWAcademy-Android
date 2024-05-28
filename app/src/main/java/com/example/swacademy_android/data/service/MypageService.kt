package com.example.swacademy_android.data.service

import com.example.swacademy_android.data.model.request.MypageEditNameRequestDto
import com.example.swacademy_android.data.model.response.MypageEditNameResponseDto
import com.example.swacademy_android.data.model.response.MypageResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface MypageService {
    @GET("/api/v1/users/my-page")
    suspend fun getMypageData() : MypageResponseDto

    @PATCH ("/api/v1/users/my-page/edit")
    suspend fun patchMypageUserName(@Body editNickName: String) :MypageEditNameResponseDto
}