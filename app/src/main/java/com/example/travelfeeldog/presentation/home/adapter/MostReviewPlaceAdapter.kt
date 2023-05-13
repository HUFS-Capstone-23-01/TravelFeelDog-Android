package com.example.travelfeeldog.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travelfeeldog.data.model.place.MostReviewPlace
import com.example.travelfeeldog.databinding.ItemHomeBestLocationBinding
import com.example.travelfeeldog.databinding.ItemHomeReviewLocationBinding

class MostReviewPlaceAdapter(private val colorList: MutableList<Int>) :
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
            binding.placeInfo = placeInfo
            binding.sivLocationMostReviewBottom.setBackgroundColor(colorList[position])
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