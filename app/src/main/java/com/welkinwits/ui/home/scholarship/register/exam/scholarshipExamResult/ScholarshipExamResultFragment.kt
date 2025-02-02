package com.welkinwits.ui.home.scholarship.register.exam.scholarshipExamResult

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.welkinwits.R
import com.welkinwits.databinding.FragmentScholarshipExamResultBinding
import com.welkinwits.service.request.scholarshipExamSubmit.ScholarshipRedeemRequest
import com.welkinwits.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ScholarshipExamResultFragment : BaseFragment(R.layout.fragment_scholarship_exam_result) {

    companion object {
        const val TAG = "QuizSuccessFragment"
    }
    private var binding: FragmentScholarshipExamResultBinding? = null
    private val viewModel: ScholarshipExamResultViewModel by viewModels()
    private var message: String? = ""
    private var score_text: String? = ""
    private var result: String? = ""


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentScholarshipExamResultBinding.bind(view)
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

        binding?.redeemScholarshipId?.setOnClickListener {
            binding?.progressBarId?.visibility = View.VISIBLE
            viewModel.getScholarshipRedeem(ScholarshipRedeemRequest("",enrollmentId?.toInt(),examId?.toInt()))
        }

        if(result.equals("fail")) {
            binding?.imageView14?.setImageDrawable(activity?.let { ContextCompat.getDrawable(it, R.drawable.cancel_ic) })
            binding?.redeemScholarshipId?.visibility = View.GONE
        } else {
            binding?.imageView14?.setImageDrawable(activity?.let { ContextCompat.getDrawable(it, R.drawable.tick_ic) })
            binding?.redeemScholarshipId?.visibility = View.VISIBLE
        }

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
        }

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