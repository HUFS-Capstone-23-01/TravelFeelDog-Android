package com.example.travelfeeldog.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travelfeeldog.R
import com.example.travelfeeldog.data.model.search.SearchingPlaceInfo
import com.example.travelfeeldog.databinding.ItemSearchResultBinding
import com.example.travelfeeldog.presentation.place.viewmodel.PlaceViewModel
import com.google.android.material.chip.Chip

class PlaceSearchResultAdapter(private val placeViewModel: PlaceViewModel):
    ListAdapter<SearchingPlaceInfo, PlaceSearchResultAdapter.PlaceSearchResultViewHolder>(PlaceSearchResultDiffCallback()) {

    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceSearchResultViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchResultBinding.inflate(layoutInflater, parent, false)
        val hashTag: Chip = layoutInflater.inflate(R.layout.item_custom_chip_hashtag, binding.cgHashtagContainer, false) as Chip
        return PlaceSearchResultViewHolder(binding, layoutInflater)
    }

    override fun onBindViewHolder(holder: PlaceSearchResultViewHolder, position: Int) {
        holder.bindItems(getItem(position))
    }

    inner class PlaceSearchResultViewHolder(private val binding: ItemSearchResultBinding, private val layoutInflater: LayoutInflater) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(placeInfo: SearchingPlaceInfo) {
            binding.viewModel = placeViewModel
            binding.placeInfo = placeInfo
            setHashTagChip(placeInfo.goodKeyWords)
            binding.executePendingBindings()

        }

        private fun setHashTagChip(keywords: List<String>) {
            binding.cgHashtagContainer.removeAllViews()
            for (keyword in keywords) {
                val chip: Chip = layoutInflater.inflate(R.layout.item_custom_chip_hashtag, binding.cgHashtagContainer, false) as Chip
                binding.cgHashtagContainer.addView(chip.apply {
                    text = "#${keyword}"
                    isCheckable = false
                })
            }
        }
    }

    class PlaceSearchResultDiffCallback() : DiffUtil.ItemCallback<SearchingPlaceInfo>() {
        override fun areItemsTheSame(
            oldItem: SearchingPlaceInfo,
            newItem: SearchingPlaceInfo
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SearchingPlaceInfo,
            newItem: SearchingPlaceInfo
        ): Boolean {
            return oldItem == newItem
        }
    }
}