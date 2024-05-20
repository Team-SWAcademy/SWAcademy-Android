package com.example.swacademy_android.presentation.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swacademy_android.data.model.request.ReturnMultiUseRequestDto
import com.example.swacademy_android.data.model.response.DetailMultiUseResponseDto
import com.example.swacademy_android.data.model.response.ReturnMultiUseResponseDto
import com.example.swacademy_android.domain.repository.DetailRepository
import com.example.swacademy_android.domain.repository.RentalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MultiUseDetailViewModel @Inject constructor(val detailRepository: DetailRepository) :
    ViewModel() {

    var detailResponseDto = MutableLiveData<DetailMultiUseResponseDto.DetailResult>()
    var returnResponseDto = MutableLiveData<ReturnMultiUseResponseDto.Result>()
    var returnResponseStatus = MutableLiveData<String>()

    fun getMultiUseDetail(useAt: String) {
        viewModelScope.launch {
            detailRepository.getDetailData(useAt).onSuccess {
                detailResponseDto.value = it.result
            }.onFailure { error ->
                if (error is HttpException) {
                    val errorBody = error.response()?.errorBody()?.string()
                    Log.e("hyeon", "detail  http 연결 실패 $errorBody")
                }
                Log.e("hyeon", "detail 실패 ${error.message}")
            }
        }
    }


    fun patchReturnMultiUse(useAt: String, locationId: Int) {
        viewModelScope.launch {
            detailRepository.patchReturnMultiUse(
                useAt,
                ReturnMultiUseRequestDto(locationId)
            ).onSuccess {
                returnResponseDto.value = it.result
                returnResponseStatus.value = it.code
                Log.e("hyeon",returnResponseStatus.value.toString())
            }.onFailure { error ->
                if (error is HttpException) {
                    val errorBody = error.response()?.errorBody()?.string()
                    Log.e("hyeon", "detail  http 연결 실패 $errorBody")
                }
                Log.e("hyeon", "detail 실패 ${error.message}")
            }
        }

    }
}