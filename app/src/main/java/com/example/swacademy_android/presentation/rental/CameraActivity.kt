package com.example.swacademy_android.presentation.rental

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.swacademy_android.R
import com.example.swacademy_android.databinding.ActivityCameraBinding
import com.example.swacademy_android.presentation.MainActivity
import com.example.swacademy_android.presentation.home.HomeViewModel
import com.example.swacademy_android.util.BindingActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CameraActivity : BindingActivity<ActivityCameraBinding>(R.layout.activity_camera) {
    private val viewModel by viewModels<CameraViewModel>()
    var isBeforeChoice : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMultiUseCategory()
        getBeforeActivity()
        viewQrScanner()
    }

    private fun getMultiUseCategory(): Int {
        val multiUseCategory = intent.getIntExtra("category", -1)
        return multiUseCategory
    }

    private fun getBeforeActivity(){
        isBeforeChoice= intent.getBooleanExtra("choice",false)
    }

    private val qrCodeLauncher =
        registerForActivityResult(ScanContract()) { result: ScanIntentResult ->

            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Scanned:" + result.contents, Toast.LENGTH_LONG).show()
                if (isBeforeChoice) {
                    showRentalDialogFragment(splitQrData(result.contents))
                }
            }
        }

    fun splitQrData(qrcodeData: String): MutableList<String> {
        var qrData: MutableList<String> = mutableListOf()
        for (i in 0 until 3) {
            qrData.add(qrcodeData.split("\n")[i])
        }
        qrData.add(getMultiUseCategory().toString())
        return qrData
    }

    private fun showRentalDialogFragment(qrData: MutableList<String>) {
        RentalDialogFragment(qrData).apply {
            show(supportFragmentManager, "RentalDialogFragment")
        }
    }

    private fun viewQrScanner() {
        val options = ScanOptions()
        options.apply {
            setOrientationLocked(false)
            setPrompt("화면에 QR코드를 스캔해주세요")
        }
        qrCodeLauncher.launch(options)
    }
}