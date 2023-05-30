package com.example.travelfeeldog.presentation.gpt.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travelfeeldog.databinding.ItemGptResponseBinding
import com.example.travelfeeldog.presentation.common.LoadingUtil

class GptResponseAdapter() :
    ListAdapter<ItemGptSearch, GptResponseAdapter.GptResponseViewHolder>(GptResponseDiffUtilCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GptResponseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGptResponseBinding.inflate(layoutInflater, parent, false)
        return GptResponseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GptResponseViewHolder, position: Int) {
        holder.bindItems(getItem(position))
    }

    inner class GptResponseViewHolder(private val binding: ItemGptResponseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(chatInfo: ItemGptSearch) {
            binding.tvRequest.text = chatInfo.userInput
            binding.tvResponse.text = chatInfo.gptResponse
            if(!binding.tvResponse.text.isNullOrEmpty()) {
                LoadingUtil.cancelTaskProgressAnimation(binding.lavResponseLoading)
            }
            binding.executePendingBindings()
        }

        private fun initLoading() {
            if(binding.tvResponse.text.isNullOrEmpty()) {
                binding.lavResponseLoading.cancelAnimation()
            }
        }
    }

    class GptResponseDiffUtilCallback() : DiffUtil.ItemCallback<ItemGptSearch>() {
        override fun areItemsTheSame(
            oldItem: ItemGptSearch,
            newItem: ItemGptSearch
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ItemGptSearch,
            newItem: ItemGptSearch
        ): Boolean {
            return oldItem == newItem
        }
    }
}