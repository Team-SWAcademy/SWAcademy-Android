package com.example.swacademy_android.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.swacademy_android.R
import com.example.swacademy_android.databinding.ActivityCertifiedCompletionBinding
import com.example.swacademy_android.util.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CertifiedCompletionActivity : BindingActivity<ActivityCertifiedCompletionBinding> (R.layout.activity_certified_completion) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnCertifiedCompletionHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}