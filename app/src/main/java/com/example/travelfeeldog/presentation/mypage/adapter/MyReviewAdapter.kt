package com.example.travelfeeldog.presentation.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travelfeeldog.data.model.mypage.MyReviewList
import com.example.travelfeeldog.databinding.ItemMyReviewBinding
import com.example.travelfeeldog.presentation.mypage.viewmodel.MyPageViewModel

class MyReviewAdapter(private val viewModel: MyPageViewModel) :
    ListAdapter<MyReviewList, MyReviewAdapter.MyReviewViewHolder>(MyReviewDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyReviewViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMyReviewBinding.inflate(layoutInflater, parent, false)
        return MyReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyReviewViewHolder, position: Int) {
        holder.bindItems(getItem(position))
    }

    inner class MyReviewViewHolder(private val binding: ItemMyReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(reviewInfo: MyReviewList) {
            binding.reviewInfo = reviewInfo
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }

    class MyReviewDiffCallback() : DiffUtil.ItemCallback<MyReviewList>() {
        override fun areItemsTheSame(
            oldItem: MyReviewList,
            newItem: MyReviewList
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MyReviewList,
            newItem: MyReviewList
        ): Boolean {
            return oldItem == newItem
        }
    }

}

