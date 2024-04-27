package com.scorepsc.ui.home.classroomCategory.quiz.quizExam

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.scorepsc.R
import com.scorepsc.databinding.FragmentQuizSuccessBinding
import com.scorepsc.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class QuizSuccessFragment : BaseFragment(R.layout.fragment_quiz_success) {

    companion object {
        const val TAG = "QuizSuccessFragment"
    }
    private var binding: FragmentQuizSuccessBinding? = null
    private var message: String? = ""
    private var score_text: String? = ""
    private var result: String? = ""
    private var chapterId: String? = ""


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentQuizSuccessBinding.bind(view)
        message = arguments?.getString("message")
        score_text = arguments?.getString("score_text")
        result = arguments?.getString("result")
        chapterId = arguments?.getString("chapterId")
        Log.d(TAG, "chapterId: "+chapterId)
        binding?.textView68?.text = message
        binding?.scoreId?.text = score_text

        binding?.redirectHome?.setOnClickListener {
            navigate(R.id.navigation_home)
        }

        binding?.redirectReport?.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("chapterId",chapterId)
            navigate(R.id.quizReportsFragment, bundle)

        }

        if(result.equals("fail")) {
            binding?.scoreId?.setTextColor(resources.getColor(R.color.fail))
            binding?.imageView14?.setImageDrawable(activity?.let { ContextCompat.getDrawable(it, R.drawable.cancel_ic) });
        } else {
            binding?.scoreId?.setTextColor(resources.getColor(R.color.psc_green))
            binding?.imageView14?.setImageDrawable(activity?.let { ContextCompat.getDrawable(it, R.drawable.tick_ic) });
        }

    }

}