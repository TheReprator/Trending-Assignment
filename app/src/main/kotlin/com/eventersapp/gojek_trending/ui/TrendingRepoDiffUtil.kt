package com.eventersapp.gojek_trending.ui

import androidx.recyclerview.widget.DiffUtil
import com.eventersapp.gojek_trending.domain.TrendingModal

class TrendingRepoDiffUtil : DiffUtil.ItemCallback<TrendingModal>() {

    override fun areItemsTheSame(oldItem: TrendingModal, newItem: TrendingModal): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: TrendingModal, newItem: TrendingModal): Boolean {
        return oldItem == newItem && (oldItem.isCollapsed == newItem.isCollapsed)
    }
}
