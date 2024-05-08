package com.example.swacademy_android.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.swacademy_android.R
import androidx.appcompat.app.AppCompatActivity
import com.example.swacademy_android.databinding.ActivityMultiuseDetailBinding
import com.example.swacademy_android.util.BindingActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
class MultiuseDetailActivity : BindingActivity<ActivityMultiuseDetailBinding>(R.layout.activity_multiuse_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.clMultiuseDetailReturnBtn.setOnClickListener {
            val options = ScanOptions()
            options.apply{
                setOrientationLocked(false)
                setPrompt("화면에 QR코드를 스캔해주세요")
            }
            qrCodeLauncher.launch(options)
        }
    }

    private val qrCodeLauncher = registerForActivityResult(ScanContract()) {
            result : ScanIntentResult ->
        if (result.contents == null) {
            Toast.makeText (this, "Cancelled" , Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText (this, "Scanned:" + result.contents, Toast.LENGTH_LONG).show()
        }
    }

}