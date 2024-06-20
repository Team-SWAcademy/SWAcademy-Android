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
    private var rentalSuccess = false
    var locationId = MutableLiveData<Int>()
    var point = MutableLiveData<Int>()
    var rentalLocationResponse = MutableLiveData<RentalLocationResponseDto.Result>()

    fun getRentalLocationData(locationId: Int, point: Int): Boolean {
        Log.e("hyeon", "${locationId} , ${point}")
        viewModelScope.launch {
            rentalRepository.getRentalLocationData(locationId, point).onSuccess {
                Log.e("hyeon", "성공 : ${it.result}")
                rentalLocationResponse.value = it.result
                rentalSuccess = true
            }.onFailure { error ->
                if (error is HttpException) {
                    val errorBody = error.response()?.errorBody()?.string()
                    Log.e("hyeon", "rental info http 연결 실패 $errorBody")
                }
                Log.e("hyeon", "rental info 실패 ${error.message}")
            }
        }
        return rentalSuccess
    }
}