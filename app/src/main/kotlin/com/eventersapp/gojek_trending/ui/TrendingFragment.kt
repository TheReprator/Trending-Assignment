package com.eventersapp.gojek_trending.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.eventersapp.gojek_trending.R
import com.eventersapp.gojek_trending.dagger.ViewModelFactory
import com.eventersapp.gojek_trending.dagger.coreComponent
import com.eventersapp.gojek_trending.databinding.FragmentTrendingBinding
import com.eventersapp.gojek_trending.util.EspressoIdlingResource
import com.eventersapp.gojek_trending.util.event.EventObserver
import com.eventersapp.gojek_trending.util.viewBinding
import javax.inject.Inject

class TrendingFragment : Fragment() {

    private val binding by viewBinding(FragmentTrendingBinding::bind)

    private lateinit var errorParentLayout: ConstraintLayout

    private lateinit var trendingRepoAdapter: TrendingRepoAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: TrendingRepoViewModal by viewModels { viewModelFactory }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.coreComponent().trendingComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return FragmentTrendingBinding.inflate(layoutInflater).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EspressoIdlingResource.increment()

        trendingRepoAdapter = TrendingRepoAdapter {
            viewModel.positionClicked(it)
        }

        disableSwipeRefresh()

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

            startSwipeToRefresh()

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
                stopSwipeToRefresh()

            showRecyclerView()
            trendingRepoAdapter.submitList(it.toList()) {
                EspressoIdlingResource.decrement()
            }
            EspressoIdlingResource.decrement()

            enableSwipeRefresh()
        }
    }

    private fun showError() {
        if (null != binding.fragTrendVb.parent)
            binding.fragTrendVb.inflate()
        else
            errorParentLayout.visibility = View.VISIBLE
    }

    private fun enableSwipeRefresh() {
        binding.fragTrendSwipe.isEnabled = true
    }

    private fun disableSwipeRefresh() {
        binding.fragTrendSwipe.isEnabled = false
    }

    private fun stopSwipeToRefresh() {
        binding.fragTrendSwipe.isRefreshing = false
    }

    private fun startSwipeToRefresh() {
        binding.fragTrendSwipe.isRefreshing = true
    }

    private fun showRecyclerView() {
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
