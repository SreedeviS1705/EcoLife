package com.welkinwits.ui.home.exam.report

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.welkinwits.R
import com.welkinwits.databinding.FragmentExamReportBinding
import com.welkinwits.service.respose.homeBanner.exam.report.ExamReportResponse
import com.welkinwits.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExamReportFragment : BaseFragment(R.layout.fragment_exam_report), IExamReportsListener {

    companion object {
        const val TAG = "ExamReportFragment"
    }

    private val viewModel: ExamReportViewModel by viewModels()
    private var binding: FragmentExamReportBinding? = null
    private var mAdapter: ExamReportAdapter ?= null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExamReportBinding.bind(view)

        viewModel.getExamReport()

        mAdapter = activity?.let { ExamReportAdapter(it, this) }
        binding?.recyclerView?.adapter = mAdapter

        viewModel.examReportResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "examReportResponse: size" + it.data?.data?.size)
            mAdapter?.updateList(it.data?.data)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {

        }

    }

    override fun clickEvent(it1: ExamReportResponse.Datum) {
        Log.d(TAG, "clickEvent: ")
        val bundle = Bundle()
        bundle.putString("id",it1?.id)
        bundle.putString("name",it1?.name)
        bundle.putString("attendOn",it1?.attentedOn)
        bundle.putString("totalMarkGained",it1?.marks)
        bundle.putString("grossMarks",it1?.marks)
        bundle.putString("passMarks",it1?.result)
        navigate(R.id.singleExamReportFragment, bundle)
    }

    /*    override fun clickEvent(it1: JobUpdatesResponse.Datum) {
            Log.d(TAG, "clickEvent: ")
            *//*val data = Bundle().apply {
            putString("subID", data.subjectId)
            putString("subName", data.subjectName)
        }*//*
        navigate(R.id.jobUpdateDetailsFragment)
    }*/
}