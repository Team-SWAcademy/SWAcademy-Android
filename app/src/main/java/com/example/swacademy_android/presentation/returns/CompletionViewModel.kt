package com.example.swacademy_android.presentation.returns

import androidx.lifecycle.ViewModel
import com.example.swacademy_android.domain.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CompletionViewModel @Inject constructor(private val detailRepository: DetailRepository) :
    ViewModel() {

}