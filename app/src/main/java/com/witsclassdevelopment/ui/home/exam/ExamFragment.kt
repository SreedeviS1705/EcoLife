package com.witsclassdevelopment.ui.home.exam

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.witsclassdevelopment.R
import com.witsclassdevelopment.adapter.ExamAdapter
import com.witsclassdevelopment.databinding.FragmentExamBinding
import com.witsclassdevelopment.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExamFragment : BaseFragment(R.layout.fragment_exam), IExamList {

    companion object {
        const val TAG = "ExamFragment"
    }

    private val viewModel: ExamViewModel by viewModels()
    private var binding: FragmentExamBinding? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExamBinding.bind(view)

        /*val chapterId = arguments?.getString("chapterId")
        val chapterName = arguments?.getString("chapterName")
        val subjectName = arguments?.getString("subjectName")

        binding?.subject?.text = subjectName*/

        binding?.statusIndicatorLayout?.root?.isVisible = true
        binding?.statusIndicatorLayout?.statusText?.text = getString(R.string.fetching_topics)
        viewModel.getExam()

        val examAdapter = ExamAdapter(this).apply {
            onItemClick {
                val data = Bundle()
                data.putString("examId", it.examId)
                navigate(R.id.singleExamFragment, data)
            }
        }
        binding?.recyclerView?.adapter = examAdapter
        /*binding?.recyclerView?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )*/

        viewModel.examResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "examResponse: size" + it.data?.data?.size)
            binding?.statusIndicatorLayout?.root?.isVisible = false
            it.data?.data?.let(examAdapter::submitList)
        }

        viewModel.examErrorMessage.observe(viewLifecycleOwner) {
            binding?.statusIndicatorLayout?.statusText?.text = it
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = true
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = false
            binding?.statusIndicatorLayout?.statusText?.isVisible = true
        }

    }

    override fun clickEvent() {
        navigate(R.id.examReportFragment)
    }
}