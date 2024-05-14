package com.example.swacademy_android.presentation.rental

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swacademy_android.data.model.request.RentalMultiUseRequestDto
import com.example.swacademy_android.domain.repository.RentalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(val rentalRepository: RentalRepository) : ViewModel() {
    private var multiUseCategory = MutableLiveData<Int>()
    var rentalMultiUse = MutableLiveData<RentalMultiUseRequestDto>()
    var responseStatusCode = MutableLiveData<String>()
    var responseSplitData = MutableLiveData<Boolean>(false)

    fun setMultiUseCategory(category: Int) {
        if (category != -1) {
            multiUseCategory.value = category
        }
    }

    fun postRentalMultiUse(rentalMultiUseRequestDto: RentalMultiUseRequestDto) {
        Log.e("hyeon",rentalMultiUseRequestDto.toString())
        viewModelScope.launch {
            rentalRepository.postRentalMultiUse(rentalMultiUseRequestDto).onSuccess {
                Log.e("hyeon", "성공 ${it.toString()}")
                responseStatusCode.value = it.code
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