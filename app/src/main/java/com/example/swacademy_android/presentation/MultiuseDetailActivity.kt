package com.example.swacademy_android.presentation

import com.example.swacademy_android.databinding.FragmentMultiuseDetailBinding
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
class MultiuseDetailActivity : AppCompatActivity() {

    lateinit var binding: FragmentMultiuseDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMultiuseDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.clMultiuseDetailReturnBtn.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }
    }

}