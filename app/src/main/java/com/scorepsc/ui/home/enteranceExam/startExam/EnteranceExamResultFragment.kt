package com.scorepsc.ui.home.enteranceExam.startExam

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.scorepsc.R
import com.scorepsc.databinding.FragmentEnteranceExamResultBinding
import com.scorepsc.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EnteranceExamResultFragment : BaseFragment(R.layout.fragment_enterance_exam_result) {

    companion object {
        const val TAG = "QuizSuccessFragment"
    }
    private var binding: FragmentEnteranceExamResultBinding? = null
    //private val viewModel: ScholarshipExamResultViewModel by viewModels()
    private var message: String? = ""
    private var score_text: String? = ""
    private var result: String? = ""


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEnteranceExamResultBinding.bind(view)
        message = arguments?.getString("message")
        score_text = arguments?.getString("score_text")
        result = arguments?.getString("result")
        val enrollmentId = arguments?.getString("enrollmentId")
        val examId = arguments?.getString("examId")
        binding?.textView68?.text = message
        binding?.scoreId?.text = score_text

        binding?.backToHomeId?.setOnClickListener {
            navigate(R.id.navigation_home)
        }

      binding?.viewReportId?.setOnClickListener {
            binding?.progressBarId?.visibility = View.VISIBLE
            /*viewModel.getScholarshipRedeem(ScholarshipRedeemRequest("",enrollmentId?.toInt(),examId?.toInt()))*/
            //navigate(R.id.enteranceReportsFragment)
          val action = EnteranceExamResultFragmentDirections.actionEnteranceStartExamFragmentToNavigationEnteranceReport()
          action?.let { it1 -> findNavController().navigate(it1) }
        }

        if(result.equals("fail")) {
            binding?.imageView14?.setImageDrawable(activity?.let { ContextCompat.getDrawable(it, R.drawable.cancel_ic) })
        } else {
            binding?.imageView14?.setImageDrawable(activity?.let { ContextCompat.getDrawable(it, R.drawable.tick_ic) })
        }
/*
        viewModel.getScholarshipRedeemResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "getScholarshipRedeemResponse")
            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed(Runnable {
                binding?.progressBarId?.visibility = View.GONE
                if(it.data?.status == true) {
                    //navigate(R.id.scholarshipRedeemSuccessfullFragment)
                    val action = ScholarshipExamResultFragmentDirections.actionScholarshipStartExamFragmentToNavigationScholarshipExamList()
                    findNavController().navigate(action)
                } else {
                    Log.d(TAG, "getScholarshipRedeemResponse: status is false")
                }
            }, 2000)
        }

        viewModel.getScholarshipRedeemErrorMessage.observe(viewLifecycleOwner) {
            Log.d(TAG, "getScholarshipRedeemErrorMessage: ")
            binding?.progressBarId?.visibility = View.GONE
        }*/

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
        binding?.progressBarId?.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

}