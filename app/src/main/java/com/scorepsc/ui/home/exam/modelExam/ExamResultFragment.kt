package com.scorepsc.ui.home.exam.modelExam

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.scorepsc.R
import com.scorepsc.databinding.FragmentSingleExamResultBinding
import com.scorepsc.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExamResultFragment : BaseFragment(R.layout.fragment_single_exam_result) {

    companion object {
        const val TAG = "ExamResultFragment"
    }
    private var binding: FragmentSingleExamResultBinding? = null


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSingleExamResultBinding.bind(view)

        val message = arguments?.getString("message")
        val score_text = arguments?.getString("score_text")
        val result = arguments?.getString("result")
        binding?.textView16?.text = message
        binding?.scoreMessageId?.text = score_text
        if(result.equals("fail")) {
            binding?.scoreMessageId?.setTextColor(resources.getColor(R.color.fail))
            binding?.imageView9?.setImageDrawable(activity?.let { ContextCompat.getDrawable(it, R.drawable.cancel_ic) })
        } else {
            binding?.scoreMessageId?.setTextColor(resources.getColor(R.color.psc_green))
            binding?.imageView9?.setImageDrawable(activity?.let { ContextCompat.getDrawable(it, R.drawable.ic_tick) })
        }


    }
}