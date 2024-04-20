package com.witsclassdevelopment.ui.home.subject

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.witsclassdevelopment.R
import com.witsclassdevelopment.databinding.FragmentSubjectBinding
import com.witsclassdevelopment.service.respose.SubjectResponse
import com.witsclassdevelopment.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubjectFragment : BaseFragment(R.layout.fragment_subject),ISubjectListener {

    companion object {
        const val TAG = "SubjectFragment"
    }

    private val viewModel: SubjectViewModel by viewModels()
    private var binding: FragmentSubjectBinding? = null
    private lateinit var mAdapter:SubjectAdapter
    private var demoClass: String?= null


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSubjectBinding.bind(view)

        binding?.statusIndicatorLayout?.root?.isVisible = true
        binding?.statusIndicatorLayout?.statusText?.text = getString(R.string.fetching_subject)
        demoClass = arguments?.getString("demoClass")
        viewModel.getSubjects()

        mAdapter = SubjectAdapter(this)
        binding?.recyclerView?.adapter = mAdapter

        viewModel.subjectResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "examResponse: size" + it.data?.data?.size)
            binding?.statusIndicatorLayout?.root?.isVisible = false
            mAdapter.updateList(it.data?.data)
        }

        viewModel.subjectErrorMessage.observe(viewLifecycleOwner) {
            binding?.statusIndicatorLayout?.statusText?.text = it
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = true
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = false
            binding?.statusIndicatorLayout?.statusText?.isVisible = true
        }

    }

    override fun clickEvent(subjectId: SubjectResponse.SubjectData) {
        Log.d(TAG, "clickEvent subjectId: $subjectId")
        if(demoClass == null) {
            val bundle = Bundle()
            bundle.putString("subjectId",""+subjectId.subjectId)
            bundle.putString("subjectName",""+subjectId.subjectName)
            navigate(R.id.liveClassBatchFragment,bundle)
        } else {
            val bundle = Bundle()
            bundle.putString("subjectId",""+subjectId.subjectId)
            navigate(R.id.demoClassSubjectFragment,bundle)
        }
    }
}