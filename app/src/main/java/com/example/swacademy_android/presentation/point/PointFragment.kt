package com.example.swacademy_android.presentation.point

import android.os.Bundle
import android.view.View
import com.example.swacademy_android.R
import com.example.swacademy_android.databinding.FragmentPointBinding
import com.example.swacademy_android.util.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PointFragment:BindingFragment<FragmentPointBinding>(R.layout.fragment_point){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}