package com.example.travelfeeldog.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travelfeeldog.data.model.place.PopularPlace
import com.example.travelfeeldog.databinding.ItemHomeBestLocationBinding

class PopularPlaceAdapter() :
    ListAdapter<PopularPlace, PopularPlaceAdapter.PopularPlaceViewHolder>(PopularPlaceDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularPlaceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeBestLocationBinding.inflate(layoutInflater, parent, false)
        return PopularPlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularPlaceViewHolder, position: Int) {
        holder.bindItems(getItem(position))
    }

    inner class PopularPlaceViewHolder(private val binding: ItemHomeBestLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(placeInfo: PopularPlace) {
            binding.placeInfo = placeInfo
            binding.executePendingBindings()
        }
    }

    class PopularPlaceDiffCallback() : DiffUtil.ItemCallback<PopularPlace>() {
        override fun areItemsTheSame(
            oldItem: PopularPlace,
            newItem: PopularPlace
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PopularPlace,
            newItem: PopularPlace
        ): Boolean {
            return oldItem == newItem
        }
    }

}