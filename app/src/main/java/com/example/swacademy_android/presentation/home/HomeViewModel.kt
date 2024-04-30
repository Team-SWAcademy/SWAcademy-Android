package com.example.swacademy_android.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

class HomeViewModel: ViewModel() {
    var isChecked = MutableLiveData<Boolean>()
    var checkedBoxIndexList = MutableLiveData<List<Int>>()

}