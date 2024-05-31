package com.example.swacademy_android.presentation.point

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.swacademy_android.R
import com.example.swacademy_android.databinding.FragmentPointBinding
import com.example.swacademy_android.util.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PointFragment : BindingFragment<FragmentPointBinding>(R.layout.fragment_point) {
    private lateinit var pointListAdapter: PointListAdapter
    private val viewModel by viewModels<PointViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPointListAdapter()
        onClickLoadMoreData()
        submitPointData()
    }

    private fun initPointListAdapter() {
        pointListAdapter = PointListAdapter {}
        binding.rvPointList.adapter = pointListAdapter
    }

    private fun submitPointData() {
        viewModel.pointListData.observe(viewLifecycleOwner) {
            pointListAdapter.submitList(it)
        }
    }

    private fun loadData(page: Int) {
        viewModel.getPointListData(page)
    }

    private fun onClickLoadMoreData() {
        pointListAdapter.setLoadMoreListener {
            loadData(viewModel.plusCurrentPage())
        }
    }
}