package com.example.travelfeeldog.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travelfeeldog.data.model.place.MostReviewPlace
import com.example.travelfeeldog.databinding.ItemHomeBestLocationBinding
import com.example.travelfeeldog.databinding.ItemHomeReviewLocationBinding
import com.example.travelfeeldog.presentation.place.viewmodel.PlaceViewModel

class MostReviewPlaceAdapter(private val placeViewModel: PlaceViewModel) :
    ListAdapter<MostReviewPlace, MostReviewPlaceAdapter.MostReviewPlaceViewHolder>(MostReviewPlaceDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostReviewPlaceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeReviewLocationBinding.inflate(layoutInflater, parent, false)
        return MostReviewPlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MostReviewPlaceViewHolder, position: Int) {
        holder.bindItems(getItem(position), position)

    }

    inner class MostReviewPlaceViewHolder(private val binding: ItemHomeReviewLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(placeInfo: MostReviewPlace, position: Int) {
            binding.viewModel = placeViewModel
            binding.placeInfo = placeInfo
            binding.executePendingBindings()
        }
    }

    class MostReviewPlaceDiffCallback() : DiffUtil.ItemCallback<MostReviewPlace>() {
        override fun areItemsTheSame(
            oldItem: MostReviewPlace,
            newItem: MostReviewPlace
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MostReviewPlace,
            newItem: MostReviewPlace
        ): Boolean {
            return oldItem == newItem
        }
    }
}