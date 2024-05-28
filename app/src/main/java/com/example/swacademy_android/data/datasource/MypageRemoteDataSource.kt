package com.example.swacademy_android.data.datasource

import com.example.swacademy_android.data.model.request.MypageEditNameRequestDto
import com.example.swacademy_android.data.service.MypageService
import javax.inject.Inject

class MypageRemoteDataSource @Inject constructor(private val mypageService: MypageService) {
    suspend fun getMypageData() = mypageService.getMypageData()
    suspend fun patchMypageUserName(editNickName: String) = mypageService.patchMypageUserName(editNickName)
}