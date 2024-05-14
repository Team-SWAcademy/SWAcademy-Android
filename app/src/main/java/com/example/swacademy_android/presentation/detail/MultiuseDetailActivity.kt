package com.example.swacademy_android.presentation.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import coil.load
import com.example.swacademy_android.R
import com.example.swacademy_android.databinding.ActivityMultiuseDetailBinding
import com.example.swacademy_android.util.BindingActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
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
                ivMultiuseCategory.load(setMultiUseCategory(it.multiUseContainer))
            }
        }
    }

    private fun setMultiUseCategory(multiUseCategory: String): Int {
        when (multiUseCategory) {
            "컵" -> {
                return R.drawable.ic_multiuse_detail_type_cup
            }

            "그릇" -> {
                return R.drawable.ic_multiuse_detail_type_bowl
            }

            "도시락" -> {
                return R.drawable.ic_multiuse_detail_type_box
            }

            else -> {
                return R.drawable.ic_multiuse_detail_type_cutlery
            }
        }
    }

    private fun setOnclickBtnReturn() {
        binding.clMultiuseDetailReturnBtn.setOnClickListener {
            val options = ScanOptions()
            options.apply {
                setOrientationLocked(false)
                setPrompt("화면에 QR코드를 스캔해주세요")
            }
            qrCodeLauncher.launch(options)
        }
    }

    private val qrCodeLauncher =
        registerForActivityResult(ScanContract()) { result: ScanIntentResult ->
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Scanned:" + result.contents, Toast.LENGTH_LONG).show()
            }
        }

}