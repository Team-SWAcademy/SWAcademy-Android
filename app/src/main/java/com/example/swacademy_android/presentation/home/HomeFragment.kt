package com.example.swacademy_android.presentation.home

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.swacademy_android.R
import com.example.swacademy_android.data.model.response.ListResponseDto
import com.example.swacademy_android.databinding.FragmentHomeBinding
import com.example.swacademy_android.presentation.MainActivity
import com.example.swacademy_android.util.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home),
    RentalListAdapter.OnCheckedChangeListener {

    private lateinit var rentalListAdapter: RentalListAdapter
    private val viewModel by viewModels<HomeViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRentalList()
    }

    override fun onCheckedChange(position: Int, isChecked: Boolean) {
    }

    private fun setRentalList() {
        var tempList: MutableList<ListResponseDto.ListData> = mutableListOf()
        tempList.apply {
            add(ListResponseDto.ListData(1, "학생회관 블루포트", "2024.04.04 15:00:00", "ㅇㅇ"))
            add(ListResponseDto.ListData(2, "서호관 블루포트", "2024.04.04 16:00:00", "ㅇㅇ"))
            add(ListResponseDto.ListData(3, "학생회관 블루포트", "2024.04.04 17:00:00", "ㅇㅇ"))
            add(ListResponseDto.ListData(4, "서호관 블루포트", "2024.04.04 18:00:00", "ㅇㅇ"))
            add(ListResponseDto.ListData(5, "서호관 블루포트", "2024.04.05 18:00:00", "ㅇㅇ"))
            add(ListResponseDto.ListData(6, "학생회관 블루포트", "2024.04.06 18:00:00", "ㅇㅇ"))
            add(ListResponseDto.ListData(7, "서호관 블루포트", "2024.04.07 18:00:00", "ㅇㅇ"))
        }
        rentalListAdapter = RentalListAdapter(this)
        binding.rvRentalList.adapter = rentalListAdapter
        rentalListAdapter.submitList(tempList)
    }
}