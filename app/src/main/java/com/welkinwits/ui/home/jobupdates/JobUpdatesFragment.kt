package com.welkinwits.ui.home.jobupdates

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.welkinwits.R
import com.welkinwits.databinding.FragmentJobUpdatesBinding
import com.welkinwits.service.respose.homeBanner.jobupdates.JobUpdatesResponse
import com.welkinwits.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobUpdatesFragment : BaseFragment(R.layout.fragment_job_updates), IJobUpdates {

    companion object {
        const val TAG = "JobUpdatesFragment"
    }

    private val viewModel: JobUpdatesViewModel by viewModels()
    private var binding: FragmentJobUpdatesBinding? = null
    private var mAdapter: JobUpdatesAdapter ?= null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentJobUpdatesBinding.bind(view)

        binding?.statusIndicatorLayout?.root?.isVisible = true
        binding?.statusIndicatorLayout?.statusText?.text = getString(R.string.fetching_job_updates)
        viewModel.getJobUpdates()


        mAdapter = JobUpdatesAdapter(this)
        binding?.recyclerView?.adapter = mAdapter

        viewModel.jobUpdatesResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "jobUpdatesResponse: size" + it.data?.data?.size)
            binding?.statusIndicatorLayout?.root?.isVisible = false
            mAdapter?.updateList(it.data?.data)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            binding?.statusIndicatorLayout?.statusText?.text = it
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = true
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = false
            binding?.statusIndicatorLayout?.statusText?.isVisible = true
        }

    }

    override fun clickEvent(it1: JobUpdatesResponse.Datum) {
        Log.d(TAG, "clickEvent: ")
        /*val data = Bundle().apply {
            putString("subID", data.subjectId)
            putString("subName", data.subjectName)
        }*/
        navigate(R.id.jobUpdateDetailsFragment)
    }

    /* override fun clickEvent(data: SubjectResponse.SubjectData) {
         Log.d(TAG, "clickEvent: ")
         val data = Bundle().apply {
             putString("subID", data.subjectId)
             putString("subName", data.subjectName)
         }
         navigate(R.id.chapterFragment, data)
     }*/
}