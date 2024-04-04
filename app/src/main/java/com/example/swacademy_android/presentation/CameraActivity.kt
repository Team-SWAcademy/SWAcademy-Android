package com.example.swacademy_android.presentation

import android.os.Bundle
import com.example.swacademy_android.R
import com.example.swacademy_android.databinding.ActivityCameraBinding
import com.example.swacademy_android.util.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CameraActivity : BindingActivity<ActivityCameraBinding> (R.layout.activity_camera) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}