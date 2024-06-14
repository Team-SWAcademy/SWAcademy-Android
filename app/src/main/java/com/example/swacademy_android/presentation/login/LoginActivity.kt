package com.example.swacademy_android.presentation.login

import android.os.Bundle
import com.example.swacademy_android.R
import com.example.swacademy_android.databinding.ActivityLoginBinding
import com.example.swacademy_android.util.BindingActivity

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onClickLoginBtn()
    }

    private fun onClickLoginBtn(){
        binding.btnKakaoLogin.setOnClickListener {

        }
    }
}