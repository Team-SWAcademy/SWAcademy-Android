package com.example.swacademy_android.presentation.point

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.swacademy_android.data.model.response.HomeResponseDto
import com.example.swacademy_android.data.model.response.PointListResponseDto
import com.example.swacademy_android.databinding.ItemCurrentRentalListBinding
import com.example.swacademy_android.databinding.ItemPointBinding
import com.example.swacademy_android.databinding.ItemPointLoadMoreBinding

class PointListAdapter(
    private val itemClick: (PointListResponseDto.Result.Content) -> (Unit)
) :
    ListAdapter<PointListResponseDto.Result.Content, ViewHolder>(diffUtil) {

    private val ITEM_TYPE = 0
    private val LOAD_MORE_TYPE = 1

    private var loadMoreListener: (() -> Unit)? = null

    fun setLoadMoreListener(listener: () -> Unit) {
        loadMoreListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_TYPE) {
            val binding =
                ItemPointBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ListViewHolder(binding)
        } else {
            val binding =
                ItemPointLoadMoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            LoadMoreViewHolder(binding)
        }
    }

    override fun getItemViewType(position: Int): Int {
        Log.e("hyeon", "${position}")
        return if (currentList[position] == null) LOAD_MORE_TYPE else ITEM_TYPE
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ListViewHolder) {
            holder.onBind(getItem(position))
        } else if (holder is LoadMoreViewHolder) {
            holder.loadMoreBtn.setOnClickListener {
                loadMoreListener?.invoke()
            }
        }
    }

    inner class ListViewHolder(
        private val binding: ItemPointBinding,
    ) : ViewHolder(binding.root) {
        fun onBind(data: PointListResponseDto.Result.Content?) {
            Log.e("hyeon", data?.useAt.toString())
            with(binding) {
                tvRentalPlace.text =
                    "대여장소: ${data?.rentalLocationName} ${data?.rentalLocationAddress}"
                tvPointDate.text = data?.useAtParsed
                tvPointNumber.text = "+${data?.point}p"
                tvReturnPlace.text =
                    "반납장소: ${data?.returnLocationName} ${data?.returnLocationAddress}"
                ivContainerType.load(data?.pointTypeImageUrl)
            }
        }
    }

    inner class LoadMoreViewHolder(private val binding: ItemPointLoadMoreBinding) :
        ViewHolder(binding.root) {
        val loadMoreBtn = binding.btnPointLoadMore
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<PointListResponseDto.Result.Content>() {
            override fun areItemsTheSame(
                oldItem: PointListResponseDto.Result.Content,
                newItem: PointListResponseDto.Result.Content
            ): Boolean {
                return oldItem.useAt == newItem.useAt
            }

            override fun areContentsTheSame(
                oldItem: PointListResponseDto.Result.Content,
                newItem: PointListResponseDto.Result.Content
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}