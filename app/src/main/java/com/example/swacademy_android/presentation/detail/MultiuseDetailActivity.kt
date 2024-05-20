package com.example.swacademy_android.presentation.detail

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import coil.load
import com.example.swacademy_android.R
import com.example.swacademy_android.databinding.ActivityMultiuseDetailBinding
import com.example.swacademy_android.presentation.camera.CameraActivity
import com.example.swacademy_android.util.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MultiuseDetailActivity :
    BindingActivity<ActivityMultiuseDetailBinding>(R.layout.activity_multiuse_detail) {

    private val viewModel by viewModels<MultiUseDetailViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setOnclickBtnReturn()
        getMultiUseDetail()
        setMultiUseDetail()
    }

    private fun getMultiUseDetail() {
        val multiUseAt = intent.getStringExtra("useAt")
        if (multiUseAt != null) {
            viewModel.getMultiUseDetail(multiUseAt)
        }
    }

    private fun setMultiUseDetail() {
        with(binding) {
            viewModel.detailResponseDto.observe(this@MultiuseDetailActivity) {
                tvMultiuseDetailLocationContent.text = "${it.locationName} ${it.locationAddress}"
                tvMultiuseDetailDateContent.text = it.useAt
                tvMultiuseDetailPointContent.text = "${it.point}P"
                ivMultiuseCategory.load(setMultiUseCategory(it.multiUseContainerId))
            }
        }
    }

    private fun setMultiUseCategory(multiUseCategory: Int): Int {
        when (multiUseCategory) {
            1 -> {
                return R.drawable.ic_multiuse_detail_type_cup
            }

            2 -> {
                return R.drawable.ic_multiuse_detail_type_bowl
            }

            3 -> {
                return R.drawable.ic_multiuse_detail_type_box
            }

            else -> {
                return R.drawable.ic_multiuse_detail_type_cutlery
            }
        }
    }

    private fun setOnclickBtnReturn() {
        binding.clMultiuseDetailReturnBtn.setOnClickListener {
            startActivity(Intent(this@MultiuseDetailActivity, CameraActivity::class.java)
                .apply{
                    putExtra("useAt",viewModel.detailResponseDto.value?.useAt)
                })
        }
    }
}