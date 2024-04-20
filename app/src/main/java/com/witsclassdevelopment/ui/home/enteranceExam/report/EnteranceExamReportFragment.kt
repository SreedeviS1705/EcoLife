package com.witsclassdevelopment.ui.home.enteranceExam.report

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.witsclassdevelopment.R
import com.witsclassdevelopment.databinding.FragmentEnteranceReportsBinding
import com.witsclassdevelopment.service.respose.homeBanner.enteranceExam.report.EnteranceExamReportsResponse
import com.witsclassdevelopment.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EnteranceExamReportFragment : BaseFragment(R.layout.fragment_enterance_reports), IEntranceExamReportsListener {

    companion object {
        const val TAG = "EnteranceExamFragment"
    }

    private val viewModel: EnteranceExamReportsViewModel by viewModels()
    private var binding: FragmentEnteranceReportsBinding? = null
    private lateinit var mAdapter:EntranceReportAdapter


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEnteranceReportsBinding.bind(view)
        viewModel.getEnteranceReports()

        mAdapter = EntranceReportAdapter(requireContext(), this)
        binding?.recyclerView?.adapter = mAdapter

        viewModel.enteranceEnteranceExamReportsResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "enteranceEnteranceExamReportsResponse: size" + it.data?.data?.size)
            if (it?.data?.data?.isEmpty() == true) {
                binding?.noDataAvailable?.visibility = View.VISIBLE
            } else {
                binding?.noDataAvailable?.visibility = View.GONE
                mAdapter.updateList(it.data?.data)
            }
        }

        viewModel.enteranceEnteranceExamReportsErrorMessage.observe(viewLifecycleOwner) {
            binding?.noDataAvailable?.visibility = View.VISIBLE
            binding?.noDataAvailable?.text = "Error Occurred"
        }
    }

    override fun clickEvent(it1: EnteranceExamReportsResponse.Datum) {
        Log.d(TAG, "clickEvent: ")
        val bundle = Bundle()
        bundle.putString("id",it1?.id)
        bundle.putString("name",it1?.name)
        bundle.putString("attendOn",it1?.addedOn)
        bundle.putString("totalMarkGained",it1?.marks)
        bundle.putString("examStatus",it1?.examStatus)
        bundle.putString("grossMarks",it1?.grossMarks)
        bundle.putString("passMarks",it1?.passMarks)
        navigate(R.id.singleEnteranceReportFragment, bundle)
    }
}