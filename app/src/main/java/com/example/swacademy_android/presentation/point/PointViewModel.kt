package com.example.swacademy_android.presentation.point

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.swacademy_android.data.model.response.PointListResponseDto
import com.example.swacademy_android.domain.repository.PointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class PointViewModel @Inject constructor(private val pointRepository: PointRepository) :
    ViewModel() {

    init {
        getPointListData(0)
    }

    var currentPage = MutableLiveData(0)
    var numOfElement: Long = 0 // 총 데이터 개수
    var hasMoreData = true

    var pointListData = MutableLiveData<List<PointListResponseDto.Result.Content?>>()
    private var allItems = mutableListOf<PointListResponseDto.Result.Content?>()

    fun plusCurrentPage(): Int{
        currentPage.value = currentPage.value!! + 1
        return currentPage.value!!
    }

    fun getPointListData(page: Int) {
        viewModelScope.launch {
            pointRepository.getPointListData(page).onSuccess {
                numOfElement = it.result.numberOfElements
                Log.e("hyeon","여기로 왔음")
                if (it.result.content.isEmpty()) {
                    Log.e("hyeon","여기로 왔음22")
                    allItems.remove(null)
                    hasMoreData = false
                }
                else{
                    Log.e("hyeon","여기로 왔음33")
                    allItems.remove(null)
                    allItems.addAll(it.result.content)
                    if (hasMoreData()) {
                        allItems.add(null)
                    }
                }
                pointListData.value = allItems.toList()
            }.onFailure { error ->
                if (error is HttpException) {
                    val errorBody = error.response()?.errorBody()?.string()
                    Log.e("hyeon", "point http 연결 실패 $errorBody")
                }
                Log.e("hyeon", "point 실패 ${error.message}")
            }
        }
    }

    fun hasMoreData() : Boolean{
        return hasMoreData
    }
}