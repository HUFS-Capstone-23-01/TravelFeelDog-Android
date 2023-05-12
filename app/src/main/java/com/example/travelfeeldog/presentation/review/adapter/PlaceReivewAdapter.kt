package com.example.travelfeeldog.presentation.review.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travelfeeldog.data.model.review.PlaceReview
import com.example.travelfeeldog.databinding.ItemReviewBinding
import com.example.travelfeeldog.presentation.place.viewmodel.PlaceViewModel

class PlaceReviewAdapter(private val viewModel: PlaceViewModel) :
    ListAdapter<PlaceReview, PlaceReviewAdapter.PlaceReviewViewHolder>(PlaceReviewAdapter.PlaceReviewDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceReviewViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemReviewBinding.inflate(layoutInflater, parent, false)
        return PlaceReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceReviewViewHolder, position: Int) {
        holder.bindItems(getItem(position))
    }

    inner class PlaceReviewViewHolder(private val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(reviewInfo: PlaceReview) {
            binding.reviewInfo = reviewInfo
            binding.executePendingBindings()
        }
    }

    class PlaceReviewDiffCallback() : DiffUtil.ItemCallback<PlaceReview>() {
        override fun areItemsTheSame(
            oldItem: PlaceReview,
            newItem: PlaceReview
        ): Boolean {
            //TODO(리뷰 아이디가 존재하지 않아서 임시방편으로 날짜로 설정함 -> 아이디 추가되면 추후 수정)
            return oldItem.createdDateTime == newItem.createdDateTime
        }

        override fun areContentsTheSame(
            oldItem: PlaceReview,
            newItem: PlaceReview
        ): Boolean {
            return oldItem == newItem
        }
    }
}