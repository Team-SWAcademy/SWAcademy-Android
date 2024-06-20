package com.example.swacademy_android.presentation.singup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.example.swacademy_android.R
import com.example.swacademy_android.databinding.ActivitySignUpBinding
import com.example.swacademy_android.util.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {

    private var editNickNameValue = ""
    private var checkedGender = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTextChangeEditNickName()
        setOnCheckedGender()
        onClickEnterBtn()
    }

    private fun onClickEnterBtn() {
        binding.clEnterBtn.setOnClickListener {
            if (editNickNameValue != "" && checkedGender != "") {
                Log.d("ej", "enter btn")
            }
        }
    }

    private fun setTextChangeEditNickName() {
        binding.etNicknameEditInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editNickNameValue = binding.etNicknameEditInput.text.toString()
                checkBtnAble()
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setOnCheckedGender() {
        binding.rgGenderCheck.setOnCheckedChangeListener{group, checkedId ->
            when(checkedId) {
                binding.rbManBtn.id -> {
                    checkedGender = "man"
                }
                binding.rbWomanBtn.id -> {
                    checkedGender = "woman"
                }
            }
            checkBtnAble()
        }
    }

    private fun checkBtnAble() {
        val enterBtn = binding.clEnterBtn
        if (editNickNameValue != "" && checkedGender != "") {
            enterBtn.setBackgroundResource(R.drawable.shape_d1e9ff_fill_e9f1ff_stroke_r50)
        }
        else {
            enterBtn.setBackgroundResource(R.drawable.shape_d9d9d9_fill_r20_rect)
        }
    }

}