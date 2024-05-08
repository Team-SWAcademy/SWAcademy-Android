package com.example.swacademy_android.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swacademy_android.data.model.response.HomeResponseDto
import com.example.swacademy_android.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor ( val homeRepository : HomeRepository): ViewModel(){

    init{
        getRentalList()
    }

    private var _rentalListResponse = MutableLiveData<List<HomeResponseDto.HomeResult.UseRes>>()
    val rentalListResponse : LiveData<List<HomeResponseDto.HomeResult.UseRes>> = _rentalListResponse
    val homeResponseResult  = MutableLiveData<HomeResponseDto.HomeResult>()
    private fun getRentalList() {
        viewModelScope.launch {
            homeRepository.getHomeData().onSuccess {
                _rentalListResponse.value = it.result.getUseResList
                homeResponseResult.value = it.result
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