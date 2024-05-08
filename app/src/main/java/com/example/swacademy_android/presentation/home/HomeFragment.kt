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
    RentalListAdapter.OnClickItemListener {

    private lateinit var rentalListAdapter: RentalListAdapter
    private val viewModel by viewModels<HomeViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRentalListAdapter()
        getHomeData()
        getRentalListData()
    }

    override fun onClickRentalListItem(position: Int) {
        TODO("Not yet implemented")
    }

    private fun initRentalListAdapter(){
        rentalListAdapter = RentalListAdapter(this)
        binding.rvRentalList.adapter = rentalListAdapter
    }

    private fun getRentalListData(){
        viewModel.rentalListResponse.observe(viewLifecycleOwner){
            rentalListAdapter.submitList(it)
        }
    }
    private fun getHomeData(){
        viewModel.homeResponseResult.observe(viewLifecycleOwner) {
            binding.tvCurrentCountContent.text = "${it.useCount}개"
            binding.tvUserName.text= it.nickname
        }
    }
}