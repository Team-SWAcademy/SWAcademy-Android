package com.example.swacademy_android.presentation

import android.os.Bundle
import com.example.swacademy_android.R
import com.example.swacademy_android.databinding.ActivityRentalInfoBinding
import com.example.swacademy_android.util.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RentalInfoActivity : BindingActivity<ActivityRentalInfoBinding>(R.layout.activity_rental_info) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}