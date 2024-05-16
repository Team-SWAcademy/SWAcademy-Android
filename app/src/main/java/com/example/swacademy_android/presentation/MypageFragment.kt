package com.example.swacademy_android.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.example.swacademy_android.R
import com.example.swacademy_android.databinding.FragmentMypageBinding
import com.example.swacademy_android.util.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MypageFragment : BindingFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickBtnEdit()
        setOnClickBtnMypage()
        addTextChangedEditName()
    }

    private var isEdit = false
    private var editNameValue = "황규혁"
    private fun setOnClickBtnEdit() {
        binding.ivEditUserNameBtn.setOnClickListener {
            binding.tvMypageUserName.visibility = View.INVISIBLE
            binding.ivEditUserNameBtn.visibility = View.INVISIBLE
            binding.etEditUserName.visibility = View.VISIBLE
            binding.clMypageBtnContent.text = "수정완료"
            isEdit = true
        }
    }

    private fun setOnClickBtnMypage() {
        binding.clMypageBtn.setOnClickListener {
            if (isEdit) {
                binding.tvMypageUserName.visibility = View.VISIBLE
                binding.ivEditUserNameBtn.visibility = View.VISIBLE
                binding.etEditUserName.visibility = View.INVISIBLE
                binding.tvMypageUserName.text = editNameValue
                binding.clMypageBtnContent.text = "로그아웃"
            }
            else {
                Toast.makeText(context, "Log out", Toast.LENGTH_SHORT).show()
            }
            isEdit = !isEdit
        }
    }

    private fun addTextChangedEditName() {
        binding.etEditUserName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editNameValue = binding.etEditUserName.text.toString()
                binding.clMypageBtn.isEnabled = editNameValue.isNotEmpty()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

}