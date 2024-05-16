package com.welkinwits.ui.home.classroomCategory.quiz.report

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.welkinwits.R
import com.welkinwits.databinding.FragmentQuizReportsBinding
import com.welkinwits.service.respose.homeBanner.quiz.report.QuizReportsResponse
import com.welkinwits.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizReportsFragment : BaseFragment(R.layout.fragment_quiz_reports), IQuizReports {

    companion object {
        const val TAG = "QuizReportsFragment"
    }
    private var binding: FragmentQuizReportsBinding? = null
    private val viewModel: QuizReportsViewModel by viewModels()
    private lateinit var mAdapter: QuizReportsAdapter

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentQuizReportsBinding.bind(view)
        val chapterId = arguments?.getString("chapterId")
        Log.d(TAG, "chapterId: "+chapterId)
        chapterId?.let { viewModel.getQuizReports(it) }
        showProgressBar(View.VISIBLE)

        mAdapter = activity?.let { QuizReportsAdapter(it, this) }!!
        binding?.quizReportRecyclerview?.adapter = mAdapter

        viewModel.getQuizReportsResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "getQuizReportsResponse size: "+it.data?.data?.size)
            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed( {
                showProgressBar(View.GONE)
                it.data?.data?.let { it1 -> mAdapter.updateList(it1) }
                if(it.data?.data?.isEmpty() == true) {
                    binding?.noQuizReportsAvailableId?.visibility = View.VISIBLE
                    binding?.noQuizReportsAvailableId?.text = "No Quiz Reports available"
                } else {
                    binding?.noQuizReportsAvailableId?.visibility = View.GONE
                }
            },1000)


        }

        viewModel.getQuizReportsErrorMessage.observe(viewLifecycleOwner) {
            Log.d(TAG, "chapterWiseStudyErrorMessage: ")
            showProgressBar(View.GONE)
            binding?.noQuizReportsAvailableId?.visibility = View.VISIBLE
            binding?.noQuizReportsAvailableId?.text = "Error loading the data"
        }


    }

    private fun showProgressBar(status:Int) {
        if(status == View.VISIBLE) {
            binding?.progressBarId?.visibility =View.VISIBLE
        } else {
            binding?.progressBarId?.visibility =View.GONE
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
        showProgressBar(View.GONE)
    }

    override fun clickEvent(item: QuizReportsResponse.Datum?) {
        val bundle = Bundle()
        bundle.putString("id",item?.id)
        bundle.putString("name",item?.name)
        bundle.putString("attendOn",item?.attendOn)
        navigate(R.id.singleQuizReportFragment, bundle)
    }
}