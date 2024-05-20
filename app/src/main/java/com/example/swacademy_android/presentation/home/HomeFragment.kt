package com.example.swacademy_android.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.swacademy_android.R
import com.example.swacademy_android.databinding.FragmentHomeBinding
import com.example.swacademy_android.presentation.detail.MultiuseDetailActivity
import com.example.swacademy_android.util.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home){

    private lateinit var rentalListAdapter: RentalListAdapter
    private val viewModel by viewModels<HomeViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRentalListAdapter()
        getHomeData()
        getRentalListData()
    }

    private fun initRentalListAdapter(){
        rentalListAdapter = RentalListAdapter{
            Intent(Intent(requireContext(), MultiuseDetailActivity::class.java)).apply {
                putExtra("useAt", it.useAt)
                startActivity(this)
            }
        }
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
            binding.tvUserName.text= "${it.nickname}님의"
            binding.tvUserPoint.text ="${it.currentPoint}P"
        }
    }
}