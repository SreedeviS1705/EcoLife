package com.scorepsc.ui.topic

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.scorepsc.PlayerActivity
import com.scorepsc.R
import com.scorepsc.databinding.FragmentTopicBinding
import com.scorepsc.service.respose.TopicResponse
import com.scorepsc.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicFragment : BaseFragment(R.layout.fragment_topic),ITopic {

    companion object {
        const val TAG = "TopicFragment"
    }

    private val viewModel: TopicViewModel by viewModels()
    private var binding: FragmentTopicBinding? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTopicBinding.bind(view)

        val chapterId = arguments?.getString("chapterId")
        val chapterName = arguments?.getString("chapterName")

        binding?.description?.text = "Video Class"

        binding?.statusIndicatorLayout?.root?.isVisible = true
        binding?.statusIndicatorLayout?.statusText?.text = getString(R.string.fetching_topics)
        viewModel.getTopics(chapterId)

        val mAdapter = TopicAdapter(this)
        binding?.recyclerView?.adapter = mAdapter

        viewModel.topicResponse.observe(viewLifecycleOwner) {
            binding?.statusIndicatorLayout?.root?.isVisible = false
            it.data?.data?.let { it1 -> mAdapter.updateList(it1) }
        }

        viewModel.topicErrorMessage.observe(viewLifecycleOwner) {
            binding?.statusIndicatorLayout?.statusText?.text = it
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = true
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = false
            binding?.statusIndicatorLayout?.statusText?.isVisible = true
        }

        binding?.statusIndicatorLayout?.statusRetry?.setOnClickListener {
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = false
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = true
            binding?.statusIndicatorLayout?.statusText?.isVisible = false
            viewModel.getTopics(chapterId)
        }

    }

    override fun clickEvent(item: TopicResponse.TopicData?) {
        Log.d(TAG, "clickEvent: ")
        val intent = Intent(requireActivity(), PlayerActivity::class.java)
        intent.putExtra("URL", item?.videoLink)
        startActivity(intent)
        item?.topicId?.let { viewModel.addToWatchedList(it) }
    }
}