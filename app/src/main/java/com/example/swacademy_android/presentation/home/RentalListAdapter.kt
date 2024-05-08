package com.example.swacademy_android.presentation.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.swacademy_android.data.model.response.HomeResponseDto
import com.example.swacademy_android.data.model.response.ListResponseDto
import com.example.swacademy_android.databinding.ItemCurrentRentalListBinding

class RentalListAdapter(private val checkedListener : OnCheckedChangeListener) : ListAdapter<HomeResponseDto.HomeResult.UseRes,RentalListAdapter.ListViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemCurrentRentalListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.onBind(currentList[position], position)
    }
    inner class ListViewHolder(
        private val binding : ItemCurrentRentalListBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: HomeResponseDto.HomeResult.UseRes, position:Int) {
            with(binding) {
                tvRentalTimeContent.text = data.useAt
                Log.e("hyeon",data.toString())
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<HomeResponseDto.HomeResult.UseRes>() {
            override fun areItemsTheSame(
                oldItem: HomeResponseDto.HomeResult.UseRes,
                newItem: HomeResponseDto.HomeResult.UseRes
            ): Boolean {
                return oldItem.rentalLocationId == newItem.rentalLocationId
            }

            override fun areContentsTheSame(
                oldItem: HomeResponseDto.HomeResult.UseRes,
                newItem: HomeResponseDto.HomeResult.UseRes
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
    interface OnCheckedChangeListener {
        fun onClickItem(position: Int, isChecked: Boolean)
    }
}