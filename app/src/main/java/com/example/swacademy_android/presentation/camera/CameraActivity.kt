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
    private var isChangedForRental = false
    private var returnUseAt = ""
    private var returnLocationId = -1
    private var isQRCodeValid = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewQrScanner()
        isChangedForRental()
        getReturnInformation()
    }

    private fun isChangedForRental() {
        isChangedForRental = intent.getBooleanExtra("rental", false)
    }

    private fun getReturnInformation() {
        returnUseAt = intent.getStringExtra("useAt").toString()
        returnLocationId = intent.getIntExtra("locationId", -1)
    }

    private val qrCodeLauncher =
        registerForActivityResult(ScanContract()) { result: ScanIntentResult ->
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Scanned:" + result.contents, Toast.LENGTH_LONG).show()
                var qrData = result.contents.toString().split("\n").toMutableList()

                if (isChangedForRental) { //qr코드 사이즈 2개, not null로 유효성 판단.
                    if (isRentalQrCodeValid(qrData)) { // 대여를 위한 전환
                        goRentalInfoActivity(qrData)
                        isQRCodeValid = true
                    }

                } else {
                    if (isReturnQrCodeValid(qrData)) { //반납 qr코드 사이즈1, not null로 유효성 판단
                        if (isReturnLocationValid(
                                qrData,
                                returnLocationId
                            )
                        ) { //반납 잘못된 location 예외처리
                            goReturnDialogFragment(qrData) //반납을 위한 전환
                            isQRCodeValid = true
                        }
                    }
                }

                if (!isQRCodeValid) {
                    showQrNotValidDialogFragment()
                }
            }
        }

    private fun isReturnLocationValid(qrData: MutableList<String>, returnLocationId: Int): Boolean {
        //TODO 반납 장소 리스트 id를 받아서 예외를 처리하자
        return true
    }

    private fun goRentalInfoActivity(qrData: MutableList<String>) {
        startActivity(
            Intent(
                this@CameraActivity,
                RentalInfoActivity::class.java
            ).apply {
                putExtra("locationId", qrData[0].toInt())
                putExtra("point", qrData[1].toInt())
            })
    }

    private fun goReturnDialogFragment(qrData: MutableList<String>) {
        qrData.add(returnUseAt)
        showReturnDialogFragment(qrData.toMutableList())
    }

    private fun showReturnDialogFragment(qrData: MutableList<String>) {
        ReturnDialogFragment(qrData).apply {
            show(supportFragmentManager, "RentalDialogFragment")
        }
    }

    private fun showQrNotValidDialogFragment() {
        QRcodeNotValidDialogFragment().apply {
            show(supportFragmentManager, "RentalDialogFragment")
        }
    }

    private fun isRentalQrCodeValid(qrData: MutableList<String>): Boolean {
        if (qrData.size != 2) return false
        if (qrData[0].toIntOrNull() == null || qrData[1].toIntOrNull() == null) return false
        return true
    }

    private fun isReturnQrCodeValid(qrData: MutableList<String>): Boolean {
        if (qrData.size != 1) return false
        if (qrData[0].toIntOrNull() == null) return false
        return true
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