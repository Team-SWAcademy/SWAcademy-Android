package com.example.swacademy_android.presentation.returns

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
import com.example.swacademy_android.databinding.FragmentReturnDialogBinding
import com.example.swacademy_android.presentation.returns.CertifiedCompletionActivity
import com.example.swacademy_android.presentation.detail.MultiUseDetailViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ReturnDialogFragment(private val qrData: MutableList<String>) : DialogFragment() {

    private val viewModel: MultiUseDetailViewModel by viewModels()
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
                viewModel.patchReturnMultiUse(qrData[1], qrData[0].toInt())
                viewModel.returnResponseStatus.observe(viewLifecycleOwner) {
                    if (it == RETURN_SUCCESS) {
                        dismiss()
                        startActivity(
                            Intent(
                                requireActivity(),
                                CertifiedCompletionActivity::class.java
                            ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        )
                    }
                }
            }
        }
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

    companion object{
        private val RETURN_SUCCESS = "RETURN_SUCCESS"
    }
}
