package com.example.swacademy_android.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.swacademy_android.R
import com.example.swacademy_android.databinding.ActivityMainBinding
import com.example.swacademy_android.databinding.FragmentCertifiedCompletionBinding

class CertifiedCompletionActivity : AppCompatActivity() {

    lateinit var binding: FragmentCertifiedCompletionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCertifiedCompletionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCertifiedCompletionHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}