package com.example.swacademy_android.presentation.returns

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.swacademy_android.R
import com.example.swacademy_android.databinding.ActivityCertifiedCompletionBinding
import com.example.swacademy_android.presentation.MainActivity
import com.example.swacademy_android.presentation.detail.MultiUseDetailViewModel
import com.example.swacademy_android.util.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CertifiedCompletionActivity :
    BindingActivity<ActivityCertifiedCompletionBinding>(R.layout.activity_certified_completion) {
    private val viewModel: MultiUseDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setReturnData()
        onClickGoHome()
    }

    private fun onClickGoHome() {
        binding.btnCertifiedCompletionHome.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }
    }

    private fun setReturnData() {
        Log.e("hyeon",viewModel.returnResponseDto.value.toString())
        with(binding) {
            tvCertifiedCompletionContentDateDetail.text =
                viewModel.returnResponseDto.value?.returnTime
            tvCertifiedCompletionContentPointDetail.text =
                viewModel.returnResponseDto.value?.currentPoint.toString()
            tvCertifiedCompletionContentLocationDetail.text =
                "${viewModel.returnResponseDto.value?.returnLocationName} ${viewModel.returnResponseDto.value?.returnLocationAddress}"
            tvCertifiedCompletionContentTitlePoint.text =
                viewModel.returnResponseDto.value?.acquiredPoint.toString()
        }
    }
}