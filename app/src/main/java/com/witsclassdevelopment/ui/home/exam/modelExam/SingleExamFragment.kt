package com.witsclassdevelopment.ui.home.exam.modelExam

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.witsclassdevelopment.R
import com.witsclassdevelopment.adapter.SingleExamListingAdapter
import com.witsclassdevelopment.databinding.FragmentSingleExamBinding
import com.witsclassdevelopment.model.QuestionModelData
import com.witsclassdevelopment.service.request.exams.submitAction.ExamSubmitRequest
import com.witsclassdevelopment.service.request.exams.submitAction.SubmitDatum
import com.witsclassdevelopment.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleExamFragment : BaseFragment(R.layout.fragment_single_exam) {

    companion object {
        const val TAG = "ExamFragment"
    }

    private val viewModel: SingleExamViewModel by viewModels()
    private var binding: FragmentSingleExamBinding? = null
    private var mAdapter: SingleExamListingAdapter? = null

    private var mExamId:String ?= null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSingleExamBinding.bind(view)

        val examId = arguments?.getString("examId")
        configureRecyclerView()

        examId?.toInt()?.let { viewModel.getSingleExam(it) }

        viewModel.singleExamResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "singleExamResponse: size" + it.data?.data?.questions?.size)
            if (it.data?.data?.questions?.size == 0) {
                Toast.makeText(activity, "No exams available", Toast.LENGTH_SHORT).show()
            } else {
                mExamId = it?.data?.data?.exam?.recordId
                binding!!.textView15.text = "" + it.data?.data?.exam?.name
                binding!!.textView14.text = "" + it.data?.data?.exam?.subjectName
                val questions = it.data?.data?.questions
                for (item in questions!!) {
                    val optionsList: ArrayList<QuestionModelData>? = ArrayList<QuestionModelData>()
                    for (innerItem in item.options!!) {
                        val model = QuestionModelData(false, innerItem, item?.questionId?.toInt(),true)
                        optionsList?.add(model)
                    }
                    item.optionsList = optionsList
                }
                mAdapter!!.updateList(questions)
                if(questions.isEmpty()) {
                    binding?.examCompleted?.visibility = View.VISIBLE
                    binding?.constraintLayout11?.visibility = View.GONE
                } else {
                    binding?.examCompleted?.visibility = View.GONE
                    binding?.constraintLayout11?.visibility = View.VISIBLE
                }
            }
        }

        viewModel.singleExamErrorMessage.observe(viewLifecycleOwner) {
            Log.d(TAG, "singleExamErrorMessage: " + it.toString())
            if(it.equals("You have aleady attended this exam.")) {
                binding?.examCompleted?.visibility = View.VISIBLE
                binding?.constraintLayout11?.visibility = View.GONE
            } else {
                binding?.examCompleted?.visibility = View.GONE
                binding?.constraintLayout11?.visibility = View.VISIBLE
            }
        }

        viewModel.singleExamSubmitAnswerResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "singleExamSubmitAnswerResponse: Success")
            Handler(Looper.getMainLooper()).postDelayed({
                Toast.makeText(activity,""+it.data?.data?.message,Toast.LENGTH_SHORT).show()
                binding?.progressContainer?.visibility = View.GONE
                binding?.constraintLayout11?.visibility = View.VISIBLE
                val bundle = Bundle()
                bundle.putString("message",it.data?.data?.message)
                bundle.putString("score_text",it.data?.data?.scoreText)
                bundle.putString("result",it.data?.data?.result)
                navigate(R.id.singleExamResultFragment,bundle)
            }, 3000)

        }
        viewModel.singleExamSubmitAnswerErrorMessage.observe(viewLifecycleOwner) {
            Log.d(TAG, "singleExamSubmitAnswerErrorMessage: " + it.toString())
            Handler(Looper.getMainLooper()).postDelayed({
                binding?.progressContainer?.visibility = View.GONE
                binding?.constraintLayout11?.visibility = View.VISIBLE
            }, 3000)
        }
        binding!!.submitBtn.setOnClickListener {
            Log.d(TAG,"submitBtn clicked"
            )
            binding?.progressContainer?.visibility = View.VISIBLE
            binding?.constraintLayout11?.visibility = View.GONE
            val mList = ArrayList<SubmitDatum>()
            if(mAdapter?.getList() != null) {
                for(item in mAdapter?.getList()!!) {
                    if(item.optionsList != null) {
                        for(innerItem in item.optionsList!!) {
                            if(innerItem.mSelectedStatus) {
                                val data = SubmitDatum()
                                data.questionId = innerItem.mQuestionId
                                data.answer = innerItem.mQuestionString
                                mList.add(data)
                            }
                        }
                    }

                }
            }
            viewModel.submitAnswer(ExamSubmitRequest("",mExamId?.toInt(),mList))
        }

    }

    private fun configureRecyclerView() {
        mAdapter = activity?.applicationContext?.let { SingleExamListingAdapter(it) }
        val layoutManager = LinearLayoutManager(activity?.applicationContext)
        binding!!.recyclerViewExamList.layoutManager = layoutManager
        binding!!.recyclerViewExamList.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding?.recyclerViewExamList?.adapter = mAdapter
    }
}