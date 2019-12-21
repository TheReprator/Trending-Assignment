package com.eventersapp.gojek_trending.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eventersapp.gojek_trending.databinding.FragmentTrendingBinding

class TrendingFragment : Fragment() {

    private lateinit var binding: FragmentTrendingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return FragmentTrendingBinding.inflate(layoutInflater).also {
            binding = it
        }.root
    }
}