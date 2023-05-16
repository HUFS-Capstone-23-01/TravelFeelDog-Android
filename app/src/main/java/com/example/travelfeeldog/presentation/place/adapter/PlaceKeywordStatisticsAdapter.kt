package com.example.travelfeeldog.presentation.place.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travelfeeldog.R
import com.example.travelfeeldog.data.model.place.KeywordStatistics
import com.example.travelfeeldog.databinding.ItemEvaluationKeywordDetailBinding

class PlaceKeywordStatisticsAdapter(private val evaluation: String) :
    ListAdapter<KeywordStatistics, PlaceKeywordStatisticsAdapter.PlaceKeywordStatisticsViewHolder>(PlaceKeywordStatisticsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceKeywordStatisticsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemEvaluationKeywordDetailBinding.inflate(layoutInflater, parent, false)
        return PlaceKeywordStatisticsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceKeywordStatisticsViewHolder, position: Int) {
        holder.bindItems(getItem(position))
    }

    inner class PlaceKeywordStatisticsViewHolder(private val binding: ItemEvaluationKeywordDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(keywordInfo: KeywordStatistics) {
            binding.keywordInfo = keywordInfo
            setKeywordCountText(binding.tvStats, evaluation)
            binding.executePendingBindings()
        }

        private fun setKeywordCountText(textView: TextView, evaluation: String) {
            when(evaluation) {
                "GOOD" -> {
                    textView.setTextAppearance(R.style.Text_14_Bold_Green)
                }
                "BAD" -> {
                    textView.setTextAppearance(R.style.Text_14_Bold_Red)
                }
            }
        }
    }

    class PlaceKeywordStatisticsDiffCallback() : DiffUtil.ItemCallback<KeywordStatistics>() {
        override fun areItemsTheSame(
            oldItem: KeywordStatistics,
            newItem: KeywordStatistics
        ): Boolean {
            return oldItem.keyWordId == newItem.keyWordId
        }

        override fun areContentsTheSame(
            oldItem: KeywordStatistics,
            newItem: KeywordStatistics
        ): Boolean {
            return oldItem == newItem
        }
    }
}