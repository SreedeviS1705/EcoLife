package com.witsclassdevelopment.ui.home.recordedClasses

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.witsclassdevelopment.R
import com.witsclassdevelopment.databinding.FragmentRecordedClassesBinding
import com.witsclassdevelopment.service.respose.SubjectResponse
import com.witsclassdevelopment.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecordedClassesFragment : BaseFragment(R.layout.fragment_recorded_classes),IRecordedClass {

    companion object {
        const val TAG = "RecordedClassesFragment"
    }

    private val viewModel: RecordedClassesViewModel by viewModels()
    private var binding: FragmentRecordedClassesBinding? = null
    private var mAdapter: RecordedClassSubjectAdapter ?= null
    private var redirectType: String? = ""


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRecordedClassesBinding.bind(view)

        binding?.statusIndicatorLayout?.root?.isVisible = true
        binding?.statusIndicatorLayout?.statusText?.text = getString(R.string.fetching_topics)
        redirectType = arguments?.getString("redirectType")
        viewModel.getSubjects()


        mAdapter = RecordedClassSubjectAdapter(this)
        binding?.recyclerView?.adapter = mAdapter

        viewModel.subjectResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "examResponse: size" + it.data?.data?.size)
            binding?.statusIndicatorLayout?.root?.isVisible = false
            binding?.description?.text = "Subjects of "+it.data?.academicLevel
            mAdapter?.updateList(it.data?.data)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            /*binding?.statusIndicatorLayout?.statusText?.text = it
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = true
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = false
            binding?.statusIndicatorLayout?.statusText?.isVisible = true*/
        }

    }

    override fun clickEvent(data: SubjectResponse.SubjectData) {
        Log.d(TAG, "clickEvent: ")
        val data = Bundle().apply {
            putString("subID", data.subjectId)
            putString("subName", data.subjectName)
            putString("redirectType",redirectType)
        }
        navigate(R.id.chapterFragment, data)
    }
}