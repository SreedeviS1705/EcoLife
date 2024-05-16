package com.welkinwits.ui.home.classroomCategory.quiz

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.welkinwits.R
import com.welkinwits.databinding.FragmentClassQuizBinding
import com.welkinwits.service.respose.homeBanner.quiz.GetQuizQuestionResponse
import com.welkinwits.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClassQuizFragment : BaseFragment(R.layout.fragment_class_quiz), IClassQuizExamList {

    companion object {
        const val TAG = "ClassQuizFragment"
    }
    private var binding: FragmentClassQuizBinding? = null
    private val viewModel: ClassQuizQuestionsViewModel by viewModels()
    private lateinit var mAdapter: ClassQuizExamListAdapter

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentClassQuizBinding.bind(view)
        val chapterId = arguments?.getString("chapterId")

        chapterId?.let { viewModel.getQuizExamList(chapterId) }

        mAdapter = activity?.let { ClassQuizExamListAdapter(it, this) }!!
        binding?.quizQuestionRecyclerview?.adapter = mAdapter

        viewModel.getQuizQuestionsResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "chapterWiseStudyResponse size: "+it.data?.data?.size)
            if(it.data?.data?.isEmpty() == true) {
                binding?.noQuizQuestionAvailableId?.visibility = View.VISIBLE
                binding?.noQuizQuestionAvailableId?.text = "No Quiz Exams available"
            } else {
                binding?.noQuizQuestionAvailableId?.visibility = View.GONE
            }
            it.data?.data?.let { it1 -> mAdapter.updateList(it1) }
        }

        viewModel.getQuizQuestionsErrorMessage.observe(viewLifecycleOwner) {
            Log.d(TAG, "chapterWiseStudyErrorMessage: ")
            binding?.noQuizQuestionAvailableId?.visibility = View.VISIBLE
            binding?.noQuizQuestionAvailableId?.text = "Error loading the data"
        }

        binding?.viewReportBtn?.setOnClickListener {
            Log.d(TAG, "viewReportBtn: ")
            val bundle = Bundle()
            bundle.putString("chapterId",chapterId)
            navigate(R.id.quizReportsFragment, bundle)
        }

    }

    override fun clickEvent(item: GetQuizQuestionResponse.Datum?) {
        Log.d(TAG, "clickEvent: ")
        val intent = Bundle()
        intent.putString("quiz_id",item?.id)
        intent.putString("chapterId",item?.chapterId)
        navigate(R.id.classQuizSingleFragment, intent)
    }
}