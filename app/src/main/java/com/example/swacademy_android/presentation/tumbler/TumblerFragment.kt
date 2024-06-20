package com.example.swacademy_android.presentation.tumbler

import android.os.Bundle
import android.view.View
import com.example.swacademy_android.R
import com.example.swacademy_android.databinding.FragmentTumblerBinding
import com.example.swacademy_android.util.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TumblerFragment: BindingFragment<FragmentTumblerBinding>(R.layout.fragment_tumbler) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickVerifyCamera()
        onClickVerifyPhoto()
        onClickQuestionMark()
    }

    private fun onClickVerifyCamera() {
        binding.clVerifyCamera.setOnClickListener {
            // 카메라로 인증하기 버튼 누를시
        }
    }

    private fun onClickVerifyPhoto() {
        binding.clVerifyPhoto.setOnClickListener {
            // 앨범 사진으로 인증하기 버튼 누를시
        }
    }

    private fun onClickQuestionMark() {
        binding.tvQuestionMark.setOnClickListener {
            // 물음표 버튼 누를시
        }
    }
}