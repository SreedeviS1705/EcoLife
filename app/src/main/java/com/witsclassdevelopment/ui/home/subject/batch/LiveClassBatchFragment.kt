package com.witsclassdevelopment.ui.home.subject.batch

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.witsclassdevelopment.R
import com.witsclassdevelopment.databinding.FragmentBatchBinding
import com.witsclassdevelopment.service.request.liveclass.batch.BatchRequest
import com.witsclassdevelopment.service.respose.homeBanner.liveClass.batch.LiveClassBatchResponse
import com.witsclassdevelopment.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LiveClassBatchFragment : BaseFragment(R.layout.fragment_batch),IBatchListener {

    companion object {
        const val TAG = "LiveClassBatchFragment"
    }

    private val viewModel: LiveClassBatchViewModel by viewModels()
    private var binding: FragmentBatchBinding? = null
    private lateinit var mAdapter: BatchAdapter

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBatchBinding.bind(view)

        binding?.statusIndicatorLayout?.root?.isVisible = true
        binding?.statusIndicatorLayout?.statusText?.text = getString(R.string.fetching_batch)
        val subjectId = arguments?.getString("subjectId")
        val subjectName = arguments?.getString("subjectName")
        binding?.textView17?.text = subjectName
        subjectId?.toInt()?.let { BatchRequest("", it) }?.let { viewModel.getLiveBatch(it) }

        mAdapter = BatchAdapter(this)
        binding?.recyclerView?.adapter = mAdapter

        viewModel.batchResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "examResponse: size" + it.data?.data?.size)
            binding?.statusIndicatorLayout?.root?.isVisible = false
            mAdapter.updateList(it.data?.data)
            if (it.data?.data?.isEmpty() == true) {
                Toast.makeText(activity, "No Batch available", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.batchErrorMessage.observe(viewLifecycleOwner) {
            binding?.statusIndicatorLayout?.statusText?.text = it
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = true
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = false
            binding?.statusIndicatorLayout?.statusText?.isVisible = true
        }

    }

    override fun clickEvent(batchId: LiveClassBatchResponse.Datum) {
        Log.d(TAG, "clickEvent: ")
        val bundle = Bundle()
        bundle.putString("batchId",batchId.batchId)
        bundle.putString("batchName",batchId.batchName)
        navigate(R.id.liveClassesFragment, bundle)
    }
}