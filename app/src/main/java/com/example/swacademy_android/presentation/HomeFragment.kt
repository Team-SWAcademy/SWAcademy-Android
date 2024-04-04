package com.example.swacademy_android.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swacademy_android.R
import com.example.swacademy_android.databinding.FragmentHomeBinding
import com.example.swacademy_android.util.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment  : BindingFragment<FragmentHomeBinding> (R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickCertificationBtn()
    }

    private fun clickCertificationBtn() {
       binding.btnCertifiedCompletion.setOnClickListener{
            startActivity(Intent(requireActivity(),CertifiedCompletionActivity::class.java))
        }
    }
}