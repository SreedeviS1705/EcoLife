package com.scorepsc.ui.home.probableQA

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.scorepsc.R
import com.scorepsc.databinding.FragmentProbableQaGroupBinding
import com.scorepsc.service.respose.homeBanner.probableQA.ProbableQAGroupResponse
import com.scorepsc.ui.base.BaseFragment
import com.scorepsc.ui.home.enteranceExam.answerKey.EnteranceExamAnswerKeyFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProbableQAGroupFragment : BaseFragment(R.layout.fragment_probable_qa_group), IProbableQAGroup {

    companion object {
        const val TAG = "PQAGFragment"
    }

    private val viewModel: ProbableQAGroupViewModel by viewModels()
    private var binding: FragmentProbableQaGroupBinding? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProbableQaGroupBinding.bind(view)
        binding?.progressBarId?.visibility = View.VISIBLE
        viewModel.getProbableQAGroup()

      val mAdapter = ProbabileQAGroupAdapter(this)
        binding?.recyclerView?.adapter = mAdapter

        viewModel.probableQAGroupResponse.observe(viewLifecycleOwner) {
            Log.d(EnteranceExamAnswerKeyFragment.TAG, "enteranceExamAnswerKeyResponse: size" + it.data?.data?.size)
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                binding?.progressBarId?.visibility = View.GONE
                if (it?.data?.data?.isEmpty() == true) {
                    binding?.noDataAvailable?.visibility = View.VISIBLE
                } else {
                    binding?.noDataAvailable?.visibility = View.GONE
                    mAdapter.updateList(it?.data?.data)
                }
            }, 500)
        }

        viewModel.probableQAGroupErrorMessage.observe(viewLifecycleOwner) {
            binding?.progressBarId?.visibility = View.GONE
            binding?.noDataAvailable?.visibility = View.VISIBLE
            binding?.noDataAvailable?.text = "Error Occurred"
        }

    }

/*    override fun clickEvent(subjectId: NewsResponse.NewsData) {
        Log.d(TAG, "clickEvent: ")
        val bundle = Bundle()
        bundle.putString("title",subjectId.heading)
        bundle.putString("image",subjectId.images)
        bundle.putString("newsdetails",subjectId.news)
        navigate(R.id.newsEventDetailsFragment, bundle)
    }*/

    override fun onPause() {
        super.onPause()
        binding?.progressBarId?.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        binding?.progressBarId?.visibility = View.GONE
    }

    override fun clickEvent(item: ProbableQAGroupResponse.Datum) {
        Log.d(TAG, "clickEvent: ")
        val bundle = Bundle()
        bundle.putString("id",item.id)
        navigate(R.id.fragmentQAAnswerKey, bundle)
    }
}