package com.welkinwits.ui.home.subject.batch.liveClasses

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.welkinwits.R
import com.welkinwits.databinding.FragmentClassesBinding
import com.welkinwits.service.request.liveclass.liveClass.LiveClassRequest
import com.welkinwits.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LiveClassesFragment : BaseFragment(R.layout.fragment_classes) {

    companion object {
        const val TAG = "LiveClassesFragment"
    }

    private val viewModel: LiveClassesViewModel by viewModels()
    private var binding: FragmentClassesBinding? = null
    private lateinit var mAdapter: ClassesAdapter
    private var mUrl: String?= null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentClassesBinding.bind(view)

        binding?.statusIndicatorLayout?.root?.isVisible = true
        binding?.statusIndicatorLayout?.statusText?.text = getString(R.string.fetching_batch)
        val batchId = arguments?.getString("batchId")
        val batchName = arguments?.getString("batchName")
        binding?.batchName?.text = batchName
        batchId?.toInt()?.let { LiveClassRequest("", it) }?.let { viewModel.getLiveBatch(it) }

        mAdapter = ClassesAdapter(activity)
        binding?.recyclerView?.adapter = mAdapter

        viewModel.liveClassResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "examResponse: size" + it.data?.data?.size)
            binding?.statusIndicatorLayout?.root?.isVisible = false
            mAdapter.updateList(it.data?.data)
            if (it.data?.data?.isEmpty() == true) {
                Toast.makeText(activity, "No Class available", Toast.LENGTH_SHORT).show()
            }
            mUrl = it.data?.data?.get(0)?.onlineUrl
        }

        viewModel.liveClassErrorMessage.observe(viewLifecycleOwner) {
            binding?.statusIndicatorLayout?.statusText?.text = it
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = true
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = false
            binding?.statusIndicatorLayout?.statusText?.isVisible = true
        }

        binding?.joinClassBtn?.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mUrl))
            activity?.startActivity(browserIntent)
        }

    }
}