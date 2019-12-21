package com.eventersapp.gojek_trending.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.eventersapp.gojek_trending.R
import com.eventersapp.gojek_trending.dagger.ViewModelFactory
import com.eventersapp.gojek_trending.dagger.coreComponent
import com.eventersapp.gojek_trending.databinding.FragmentTrendingBinding
import com.eventersapp.gojek_trending.ui.di.DaggerTrendingComponent
import com.eventersapp.gojek_trending.util.event.EventObserver
import javax.inject.Inject

class TrendingFragment : Fragment() {

    private lateinit var binding: FragmentTrendingBinding
    private lateinit var errorParentLayout: ConstraintLayout

    @Inject
    lateinit var trendingRepoAdapter: TrendingRepoAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: TrendingRepoViewModal by lazy {
        ViewModelProvider(viewModelStore, viewModelFactory).get(TrendingRepoViewModal::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerTrendingComponent.factory().create(context.coreComponent()).inject(this)
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.fragTrendRecy) {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                LineDividerDecorator(context, R.dimen.divider_height, R.color.colorAccent)
            )
            adapter = trendingRepoAdapter
        }

        initializeListener()

        initializeObserver()
    }

    private fun initializeListener() {

        binding.fragTrendSwipe.setOnRefreshListener {

            binding.fragTrendSwipe.isRefreshing = true

            viewModel.getTrendingUseCaseWithPullToRefresh()
        }

        binding.fragTrendVb.setOnInflateListener { _, inflated ->
            errorParentLayout = inflated as ConstraintLayout
            errorParentLayout.findViewById<Button>(R.id.interent_bt_retry).setOnClickListener {
                hideError()
                viewModel.retry()
            }
        }
    }

    private fun initializeObserver() {

        viewModel.isError.observe(viewLifecycleOwner, EventObserver {
            if (it)
                showError()
            else
                hideError()
        })

        viewModel.isLoading.observe(viewLifecycleOwner, EventObserver {
            if (it)
                showLoaderFragment()
            else
                hideLoaderFragment()
        })

        viewModel.trendingList.observe(viewLifecycleOwner) {
            if (binding.fragTrendSwipe.isRefreshing)
                binding.fragTrendSwipe.isRefreshing = false

            showRecyclerView()
            trendingRepoAdapter.submitList(it)
        }
    }

    private fun showError() {
        if (null != binding.fragTrendVb.parent)
            binding.fragTrendVb.inflate()
        else
            errorParentLayout.visibility = View.VISIBLE
    }

    private fun showRecyclerView()
    {
        binding.fragTrendRecy.visibility = View.VISIBLE
    }

    private fun hideError() {
        errorParentLayout.visibility = View.GONE
    }

    private fun hideLoaderFragment() {
        binding.fragTrendPb.visibility = View.GONE
    }

    private fun showLoaderFragment() {
        binding.fragTrendPb.visibility = View.VISIBLE
    }
}