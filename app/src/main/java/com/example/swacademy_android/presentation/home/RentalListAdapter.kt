package com.example.swacademy_android.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.swacademy_android.data.model.response.ListResponseDto
import com.example.swacademy_android.databinding.ItemCurrentRentalListBinding

class RentalListAdapter(private val checkedListener : OnCheckedChangeListener) : ListAdapter<ListResponseDto.ListData,RentalListAdapter.ListViewHolder>(diffUtil) {
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
        fun onBind(data: ListResponseDto.ListData, position:Int) {
            with(binding) {
                tvCafeNameContent.text = data.cafeName
                tvRentalTimeContent.text = data.rentalTime
                /*ivCafeThumbnail.load(data.cafeThumbnail){
                    transformations(RoundedCornersTransformation(10f))
                }*/
                checkboxRentalCup.setOnCheckedChangeListener{ _, isChecked ->
                    checkedListener.onCheckedChange(position,isChecked)
                }
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ListResponseDto.ListData>() {
            override fun areItemsTheSame(
                oldItem: ListResponseDto.ListData,
                newItem: ListResponseDto.ListData
            ): Boolean {
                return oldItem.idx == newItem.idx
            }

            override fun areContentsTheSame(
                oldItem: ListResponseDto.ListData,
                newItem: ListResponseDto.ListData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
    interface OnCheckedChangeListener {
        fun onCheckedChange(position: Int, isChecked: Boolean)
    }
}