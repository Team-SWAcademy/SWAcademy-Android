package com.example.swacademy_android.presentation.rental.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swacademy_android.data.model.response.RentalLocationResponseDto
import com.example.swacademy_android.domain.repository.RentalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class RentalInfoViewModel @Inject constructor(private val rentalRepository: RentalRepository) :
    ViewModel() {
    var locationId = MutableLiveData<Int>()
    var point = MutableLiveData<Int>()
    var rentalLocationResponse = MutableLiveData<RentalLocationResponseDto.Result>()
    fun splitQrData(qrData: List<String>) {
        locationId.value = qrData[0].toInt()
        point.value = qrData[1].toInt()
    }

    fun getRentalLocationData(locationId: Int, point: Int) {
        Log.e("hyeon", "${locationId} , ${point}")
        viewModelScope.launch {
            rentalRepository.getRentalLocationData(locationId, point).onSuccess {
                Log.e("hyeon", "성공 : ${it.result}")
                rentalLocationResponse.value = it.result
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