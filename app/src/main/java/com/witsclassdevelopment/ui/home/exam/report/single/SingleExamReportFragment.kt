package com.witsclassdevelopment.ui.home.exam.report.single

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.witsclassdevelopment.R
import com.witsclassdevelopment.databinding.FragmentSingleExamReportBinding
import com.witsclassdevelopment.model.QuestionModelData
import com.witsclassdevelopment.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleExamReportFragment : BaseFragment(R.layout.fragment_single_exam_report) {

    companion object {
        const val TAG = "SEReportFragment"
    }
    private var binding: FragmentSingleExamReportBinding? = null
    private val viewModel: SingleExamReportViewModel by viewModels()
    private lateinit var mAdapter: SingleExamReportQuestionAdapter

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSingleExamReportBinding.bind(view)

        val id = arguments?.getString("id")
        val name = arguments?.getString("name")
        val attendOn = arguments?.getString("attendOn")
        val totalMarkGained = arguments?.getString("totalMarkGained")
        val examStatus = arguments?.getString("examStatus")
        val grossMarks = arguments?.getString("grossMarks")
        val passMarks = arguments?.getString("passMarks")
        binding?.textView59?.text = name
        binding?.textView599?.text = "Last Attended on : "+attendOn

        id?.toInt()?.let { viewModel.getSingleExamReport(it)
                showProgressBar(View.VISIBLE)
            }

                mAdapter = activity?.let { SingleExamReportQuestionAdapter(it) }!!
                binding?.reportRecyclerview?.adapter = mAdapter

                viewModel.singleExamReportReportResponse.observe(viewLifecycleOwner) {
                    Log.d(TAG, "singleExamReportReportResponse "+it)
                    val handler = Handler(Looper.getMainLooper())
                    handler.postDelayed( {
                        showProgressBar(View.GONE)
                        //it.data?.data?.let { it1 -> mAdapter.updateList(it1) }
                        if(it.data?.data?.report?.isEmpty() == true) {
                            binding?.noQuizReportsAvailableId?.visibility = View.VISIBLE
                            binding?.noQuizReportsAvailableId?.text = "No Quiz Reports available"
                        } else {
                            binding?.noQuizReportsAvailableId?.visibility = View.GONE
                            binding?.textView82?.text = Html.fromHtml("Total Marks Gained : <font color='black'>"+it.data?.data?.analytics?.totalMarksGained+"</font>")
                            binding?.textView83?.text = Html.fromHtml("Total Marks Loss : <font color='black'>"+it.data?.data?.analytics?.totalMarksLoss+"</font>")
                            if(it.data?.data?.analytics?.resultStatus.equals("Pass")) {
                                binding?.textView78?.text = Html.fromHtml("Result Status : <font color='green'>"+it.data?.data?.analytics?.resultStatus+"</font>")
                            } else {
                                binding?.textView78?.text = Html.fromHtml("Result Status : <font color='red'>"+it.data?.data?.analytics?.resultStatus+"</font>")
                            }
                            binding?.textView81?.text = Html.fromHtml("Questions Attended : <font color='green'>"+it.data?.data?.analytics?.questionsAttended+"</font>")
                            binding?.textView80?.text = Html.fromHtml("Total Quiz Mark  : <font color='black'>"+it.data?.data?.analytics?.totalExamMark+"</font>")
                            binding?.passMarkId?.text = Html.fromHtml("Pass Mark  :  <font color='black'>"+it.data?.data?.analytics?.passMark+"</font>")
                            val questions = it.data?.data?.report
                            for (item in questions!!) {
                                val optionsList: ArrayList<QuestionModelData>? = ArrayList<QuestionModelData>()
                                for (innerItem in item.options!!) {
                                    var selectionStatus = false
                                    var mWrongAnswerStatus: String = ""
                                    var mCorrectAnswerStatus: String = ""
                                    if(innerItem == item.answer) {
                                        if(item.answer != item.correctAnswer) {
                                            mWrongAnswerStatus = "fail"
                                            mCorrectAnswerStatus = item.correctAnswer.toString()
                                        }
                                        selectionStatus = true
                                    }
                                    val model = QuestionModelData(selectionStatus, innerItem, 0, false, mWrongAnswerStatus, mCorrectAnswerStatus)
                                    optionsList?.add(model)
                                }
                                item.optionsList = optionsList
                            }

                            questions.let { it1 -> mAdapter.updateList(questions) }
                        }
                    },1000)
                }

                viewModel.singleExamReportReportErrorMessage.observe(viewLifecycleOwner) {
                    Log.d(TAG, "singleExamReportReportErrorMessage: ")
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
}