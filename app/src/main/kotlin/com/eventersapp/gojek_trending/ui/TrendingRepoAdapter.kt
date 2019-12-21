package com.eventersapp.gojek_trending.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.eventersapp.gojek_trending.R
import com.eventersapp.gojek_trending.databinding.RowTrendingBinding
import com.eventersapp.gojek_trending.domain.TrendingModal

class TrendingRepoAdapter : ListAdapter<TrendingModal, VHTrendingRepo>(TrendingRepoDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHTrendingRepo {
        val binding = RowTrendingBinding.inflate(LayoutInflater.from(parent.context))
        return VHTrendingRepo(binding)
    }

    override fun onBindViewHolder(holder: VHTrendingRepo, position: Int) {
        val repoModal = getItem(position)

        holder.binding.trendImgUser.load(repoModal.avatar) {
            crossfade(true)
            placeholder(R.drawable.ic_nointernet_connection)
            transformations(CircleCropTransformation())
        }

        holder.binding.trendTvUserName.text = repoModal.author
        holder.binding.trendTvRepoName.text = repoModal.name

        holder.binding.trendLayCollapseView.visibility = if (repoModal.isCollapsed)
            View.GONE
        else
            View.VISIBLE

        holder.binding.trendTvRepoDescription.text = repoModal.description
        holder.binding.trendTvLanguage.text = repoModal.language
        holder.binding.trendTvStar.text = repoModal.stars.toString()
        holder.binding.trendTvFork.text = repoModal.forks.toString()
    }
}


class VHTrendingRepo(
    val binding: RowTrendingBinding
) : RecyclerView.ViewHolder(binding.root)


