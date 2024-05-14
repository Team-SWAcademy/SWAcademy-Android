package com.example.swacademy_android.presentation.store

import android.os.Bundle
import android.view.View
import com.example.swacademy_android.R
import com.example.swacademy_android.databinding.FragmentStoreBinding
import com.example.swacademy_android.util.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreFragment: BindingFragment<FragmentStoreBinding>(R.layout.fragment_store) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}