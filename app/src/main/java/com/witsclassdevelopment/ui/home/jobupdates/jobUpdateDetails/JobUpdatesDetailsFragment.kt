package com.witsclassdevelopment.ui.home.jobupdates.jobUpdateDetails

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.witsclassdevelopment.R
import com.witsclassdevelopment.databinding.FragmentJobUpdateDetailsBinding
import com.witsclassdevelopment.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class JobUpdatesDetailsFragment : BaseFragment(R.layout.fragment_job_update_details) {

    companion object {
        const val TAG = "Details"
    }

    private val viewModel: JobUpdateDetailsViewModel by viewModels()
    private var binding: FragmentJobUpdateDetailsBinding? = null
    private var mAdapter: JobUpdateDetailsAdapter ?= null
    private var mDocLink = ""

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentJobUpdateDetailsBinding.bind(view)

        binding?.statusIndicatorLayout?.root?.isVisible = true
        binding?.statusIndicatorLayout?.statusText?.text = getString(R.string.fetching_job_updates_details)
        viewModel.getJobUpdateDetails()


        mAdapter = JobUpdateDetailsAdapter()
        binding?.recyclerView?.adapter = mAdapter

        viewModel.jobUpdatesResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "Response: size" + it.data?.data)
            binding?.statusIndicatorLayout?.root?.isVisible = false
            binding?.textView18?.text = it.data?.data?.title
            binding?.textView19?.text = it.data?.data?.description
            binding?.textView20?.text = it.data?.data?.place
            mAdapter?.updateList(it.data?.data?.details)
            mDocLink = it.data?.data?.applyLink.toString()
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            binding?.statusIndicatorLayout?.statusText?.text = it
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = true
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = false
            binding?.statusIndicatorLayout?.statusText?.isVisible = true
            viewModel.getJobUpdateDetails()
        }

        binding?.applyButton?.setOnClickListener {
            val markerIntent = Intent(Intent.ACTION_VIEW)
            if(mDocLink != "") {
                markerIntent.data =
                    Uri.parse(mDocLink)
                startActivity(markerIntent)
            } else {
                Toast.makeText(activity,"Cannot open this document",Toast.LENGTH_SHORT).show()
            }
        }

    }
}