package com.example.swacademy_android.presentation.camera

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.swacademy_android.R
import com.example.swacademy_android.databinding.ActivityCameraBinding
import com.example.swacademy_android.presentation.rental.RentalInfoActivity
import com.example.swacademy_android.presentation.rental.viewmodel.RentalInfoViewModel
import com.example.swacademy_android.presentation.returns.ReturnDialogFragment
import com.example.swacademy_android.util.BindingActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CameraActivity : BindingActivity<ActivityCameraBinding>(R.layout.activity_camera) {
    private val viewModel by viewModels<RentalInfoViewModel>()
    private var isChangedForRental = false
    private var returnUseAt = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewQrScanner()
        isChangedForRental()
        getReturnsUseAt()
    }

    private fun isChangedForRental() {
        isChangedForRental = intent.getBooleanExtra("rental", false)
    }

    private fun getReturnsUseAt(){
        returnUseAt = intent.getStringExtra("useAt").toString()
    }

    private val qrCodeLauncher =
        registerForActivityResult(ScanContract()) { result: ScanIntentResult ->
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Scanned:" + result.contents, Toast.LENGTH_LONG).show()
                var qrData = result.contents.toString().split("\n").toMutableList()
                if (isChangedForRental) {
                    startActivity(
                        Intent(
                            this@CameraActivity,
                            RentalInfoActivity::class.java
                        ).apply {
                            putExtra("locationId", qrData[0].toInt())
                            putExtra("point", qrData[1].toInt())
                        })
                } else {
                    qrData.add(returnUseAt)
                    showReturnDialogFragment(qrData.toMutableList())
                }
            }
        }

    private fun showReturnDialogFragment(qrData: MutableList<String>) {
        ReturnDialogFragment(qrData).apply {
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