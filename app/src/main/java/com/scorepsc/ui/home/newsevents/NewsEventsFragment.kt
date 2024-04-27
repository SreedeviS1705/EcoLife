package com.scorepsc.ui.home.newsevents

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.scorepsc.R
import com.scorepsc.databinding.FragmentNewsEventsBinding
import com.scorepsc.service.respose.NewsResponse
import com.scorepsc.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsEventsFragment : BaseFragment(R.layout.fragment_news_events), INewsListener {

    companion object {
        const val TAG = "NewsEventsFragment"
    }

    private val viewModel: NewsEventViewModel by viewModels()
    private var binding: FragmentNewsEventsBinding? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsEventsBinding.bind(view)

        binding?.statusIndicatorLayout?.root?.isVisible = true
        binding?.statusIndicatorLayout?.statusText?.text = getString(R.string.fetching_news_events)
        viewModel.getNews()

        val mAdapter = NewsEventsAdapter(this)
        binding?.recyclerView?.adapter = mAdapter

        viewModel.newsResponse.observe(viewLifecycleOwner) {
            binding?.statusIndicatorLayout?.root?.isVisible = false
            it.data?.data?.let { it1 -> mAdapter.updateList(it1) }
        }

        viewModel.newsErrorMessage.observe(viewLifecycleOwner) {
            binding?.statusIndicatorLayout?.statusText?.text = it
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = true
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = false
            binding?.statusIndicatorLayout?.statusText?.isVisible = true
        }

        binding?.statusIndicatorLayout?.statusRetry?.setOnClickListener {
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = false
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = true
            binding?.statusIndicatorLayout?.statusText?.isVisible = false
            viewModel.getNews()
        }

    }

    override fun clickEvent(subjectId: NewsResponse.NewsData) {
        Log.d(TAG, "clickEvent: ")
        val bundle = Bundle()
        bundle.putString("title",subjectId.heading)
        bundle.putString("image",subjectId.images)
        bundle.putString("newsdetails",subjectId.news)
        navigate(R.id.newsEventDetailsFragment, bundle)
    }
}