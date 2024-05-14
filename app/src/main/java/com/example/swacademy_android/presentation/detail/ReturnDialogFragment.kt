package com.example.swacademy_android.presentation.detail

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.swacademy_android.data.model.request.RentalMultiUseRequestDto
import com.example.swacademy_android.data.model.request.ReturnMultiUseRequestDto
import com.example.swacademy_android.databinding.FragmentRentalDialogBinding
import com.example.swacademy_android.databinding.FragmentReturnDialogBinding
import com.example.swacademy_android.presentation.MainActivity
import com.example.swacademy_android.presentation.rental.CameraViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RentalDialogFragment(private val qrData: MutableList<String>) : DialogFragment() {

    private val viewModel: CameraViewModel by viewModels()
    private var _binding: FragmentReturnDialogBinding? = null
    private val binding get() = requireNotNull(_binding) { "ReturnDialogFragment is null" }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReturnDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backgroundDesign()
        setBtnClickEvent()
    }

    private fun backgroundDesign() {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun setBtnClickEvent() {
        with(binding) {
            btnCancel.setOnClickListener {
                dismiss()
            }
            btnConfirm.setOnClickListener {
            }
        }
    }

    fun setRentalMultiUse(qrCodeDataList: List<String>): ReturnMultiUseRequestDto {
        return ReturnMultiUseRequestDto(
            qrCodeDataList[0],
            qrCodeDataList[1]
        )
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
