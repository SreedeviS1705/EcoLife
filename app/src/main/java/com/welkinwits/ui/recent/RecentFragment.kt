package com.welkinwits.ui.recent

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.welkinwits.PlayerActivity
import com.welkinwits.R
import com.welkinwits.adapter.TopicAdapter
import com.welkinwits.databinding.FragmentRecentBinding
import com.welkinwits.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecentFragment : BaseFragment(R.layout.fragment_recent) {


    private val viewModel: RecentViewModel by activityViewModels()
    private var binding: FragmentRecentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRecentBinding.bind(view)

        binding?.statusIndicatorLayout?.statusIndicator?.isVisible = true
        binding?.statusIndicatorLayout?.root?.isVisible = true
        binding?.statusIndicatorLayout?.statusText?.text = getString(R.string.fetching_recent)
        viewModel.getRecentlyWatched()

        val topicAdapter = TopicAdapter().apply {
            onItemClick {
                val intent = Intent(requireActivity(), PlayerActivity::class.java)
                intent.putExtra("URL", it.videoLink)
                startActivity(intent)
            }
        }
        binding?.recyclerView?.adapter = topicAdapter
        binding?.recyclerView?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        viewModel.watchedResponse.observe(viewLifecycleOwner, {
            if (it == null) return@observe
            if (it.data?.data?.size == 0) {
                binding?.statusIndicatorLayout?.statusIndicator?.isVisible = false
                binding?.statusIndicatorLayout?.statusText?.text = getString(R.string.no_data)
                viewModel.watchedResponse.value = null
            } else {
                binding?.statusIndicatorLayout?.root?.isVisible = false
                it.data?.data?.let(topicAdapter::submitList)
            }
        })

        viewModel.watchedErrorMessage.observe(viewLifecycleOwner, {
            binding?.statusIndicatorLayout?.statusText?.text = it
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = true
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = false
            binding?.statusIndicatorLayout?.statusText?.isVisible = true

        })

        binding?.statusIndicatorLayout?.statusRetry?.setOnClickListener {
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = false
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = true
            binding?.statusIndicatorLayout?.statusText?.isVisible = false
            viewModel.getRecentlyWatched()
        }

    }

}