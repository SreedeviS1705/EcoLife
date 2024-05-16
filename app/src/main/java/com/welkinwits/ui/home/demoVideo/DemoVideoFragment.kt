package com.welkinwits.ui.home.demoVideo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.welkinwits.PlayerActivity
import com.welkinwits.R
import com.welkinwits.databinding.FragmentDemoVideosBinding
import com.welkinwits.service.respose.homeBanner.demoVideos.DemoVideosResponse
import com.welkinwits.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DemoVideoFragment : BaseFragment(R.layout.fragment_demo_videos),IDemoVideo {

    companion object {
        const val TAG = "DemoVideoFragment"
    }

    private val viewModel: DemoVideosViewModel by viewModels()
    private var binding: FragmentDemoVideosBinding? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDemoVideosBinding.bind(view)

        binding?.statusIndicatorLayout?.root?.isVisible = true
        binding?.statusIndicatorLayout?.statusText?.text = getString(R.string.fetching_demo_videos)
        viewModel.getDemoVideos()

        val mAdapter = DemoVideoAdapter(this)
        binding?.recyclerView?.adapter = mAdapter

        viewModel.demoVideosResponse.observe(viewLifecycleOwner) {
            binding?.statusIndicatorLayout?.root?.isVisible = false
            it.data?.data?.let { it1 -> mAdapter.updateList(it1) }
        }

        viewModel.demoVideosErrorMessage.observe(viewLifecycleOwner) {
            binding?.statusIndicatorLayout?.statusText?.text = it
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = true
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = false
            binding?.statusIndicatorLayout?.statusText?.isVisible = true
        }

        binding?.statusIndicatorLayout?.statusRetry?.setOnClickListener {
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = false
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = true
            binding?.statusIndicatorLayout?.statusText?.isVisible = false
            viewModel.getDemoVideos()
        }

    }

    override fun clickEvent(item: DemoVideosResponse.Datum?) {
        val intent = Intent(requireActivity(), PlayerActivity::class.java)
        intent.putExtra("URL", item?.videoLink)
        startActivity(intent)

    }
}