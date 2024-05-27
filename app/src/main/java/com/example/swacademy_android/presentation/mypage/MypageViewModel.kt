package com.example.swacademy_android.presentation.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swacademy_android.data.model.response.HomeResponseDto
import com.example.swacademy_android.data.model.response.MypageResponseDto
import com.example.swacademy_android.domain.repository.MypageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MypageViewModel @Inject constructor(private val mypageRepository: MypageRepository) :
    ViewModel() {
    init {
        getMypageData()
    }

    private var _mypageResponse = MutableLiveData<MypageResponseDto.Result>()
    val mypageResponse: LiveData<MypageResponseDto.Result> = _mypageResponse
    private fun getMypageData() {
        viewModelScope.launch {
            mypageRepository.getMypageData().onSuccess {
                _mypageResponse.value = it.result
            }.onFailure { error ->
                if (error is HttpException) {
                    val errorBody = error.response()?.errorBody()?.string()
                    Log.e("hyeon", "next question http 연결 실패 $errorBody")
                }
                Log.e("hyeon", "next question 실패 ${error.message}")
            }
        }
    }
}