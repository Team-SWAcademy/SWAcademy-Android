package com.example.swacademy_android.presentation.rental

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
import com.example.swacademy_android.databinding.FragmentRentalDialogBinding
import com.example.swacademy_android.presentation.MainActivity
import com.example.swacademy_android.presentation.camera.CameraViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RentalDialogFragment(private val rentalData : RentalMultiUseRequestDto) : DialogFragment() {

    private val viewModel: CameraViewModel by viewModels ()
    private var _binding: FragmentRentalDialogBinding? = null
    private val binding get() = requireNotNull(_binding) { "RentalDialogFrgament is null" }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRentalDialogBinding.inflate(inflater, container, false)
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
                viewModel.postRentalMultiUse(rentalData)
                viewModel.responseStatusCode.observe(viewLifecycleOwner) {
                    if (it == "USE2000") {
                        Log.e("hyeon","성공 use2000")
                        dismiss()
                        startActivity(
                            Intent(
                                requireActivity(),
                                MainActivity::class.java
                            ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        )
                    }
                }
            }
        }
    }

    fun setRentalMultiUse(qrCodeDataList: List<String>): List<Int> {
        return listOf(
            qrCodeDataList[0].toInt(),
            qrCodeDataList[1].toInt()
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