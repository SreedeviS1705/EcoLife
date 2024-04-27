package com.scorepsc.ui.home.scholarship.register.exam

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.scorepsc.R
import com.scorepsc.databinding.FragmentScholarshipStartExamBinding
import com.scorepsc.model.QuestionModelData
import com.scorepsc.service.request.ScholarshipStartExamRequest
import com.scorepsc.service.request.scholarshipExamSubmit.ScholarshipExamSubmitRequest
import com.scorepsc.service.request.scholarshipExamSubmit.SubmitDatum
import com.scorepsc.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class ScholarshipStartExamFragment : BaseFragment(R.layout.fragment_scholarship_start_exam) {

    companion object {
        const val TAG = "SshipSExamFragment"
    }
    private var binding: FragmentScholarshipStartExamBinding? = null
    private val viewModel: ScholarshipRegisterDetailsDisplayViewModel by viewModels()
    private lateinit var mAdapter: ScholarshipStartExamAdapter
    private var  countDownTimer: CountDownTimer?= null
    private var examId: String?= null
    private var examRowId: String?= null


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentScholarshipStartExamBinding.bind(view)
        val enrollmentId = arguments?.getString("enrollmentId")
        examId = arguments?.getString("examId")

        enrollmentId?.let { viewModel.getScholarshipStartExam(ScholarshipStartExamRequest(null, enrollmentId.toInt())) }

        mAdapter = activity?.let { ScholarshipStartExamAdapter(it) }!!
        binding?.listRecyclerview?.adapter = mAdapter

        viewModel.getScholarshipStartExamResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "getQuizSingleQuestionsResponse size: ")
            if(it.message?.equals("Hey, You have already Attended the Exam!") == true) {
                binding?.noListAvailableId?.visibility = View.VISIBLE
                binding?.textView58?.visibility = View.GONE
                binding?.submitBtn?.visibility = View.GONE
                binding?.textView74?.visibility = View.GONE
                binding?.noListAvailableId?.text = "Hey, You have already Attended the Exam!"
            } else if(it.data?.data?.questions?.isEmpty() == true || it.data?.data?.exam?.time ==  null) {
                binding?.noListAvailableId?.visibility = View.VISIBLE
                binding?.textView58?.visibility = View.GONE
                binding?.submitBtn?.visibility = View.GONE
                binding?.textView74?.visibility = View.GONE
                binding?.noListAvailableId?.text = "No Quiz available"
            } else {
                examRowId = it.data.data.exam!!.examRowId
                binding?.noListAvailableId?.visibility = View.GONE
                binding?.textView58?.visibility = View.VISIBLE
                binding?.submitBtn?.visibility = View.VISIBLE
                binding?.textView74?.visibility = View.VISIBLE

                val questions = it.data?.data?.questions
                for (item in questions!!) {
                    val optionsList: ArrayList<QuestionModelData>? = ArrayList<QuestionModelData>()
                    for (innerItem in item.options!!) {
                        val model = QuestionModelData(false, innerItem, item?.id?.toInt(),true)
                        optionsList?.add(model)
                    }
                    item.optionsList = optionsList
                }

                questions.let { it1 -> mAdapter.updateList(questions) }
                //Start Timer
                countDownTimer = object : CountDownTimer(TimeUnit.MINUTES.toMillis(it.data.data.exam!!.time!!.toLong()), 1000) {

                    override fun onTick(millisUntilFinished: Long) {
                        binding?.timerTextId?.text = "" + millisUntilFinished / 1000
                    }

                    override fun onFinish() {
                        Toast.makeText(activity, "Exam Time Over", Toast.LENGTH_LONG).show()
                        Log.d(TAG, "examApiCall onFinish: ")
                        submitExamResult()
                    }
                }.start()
            }


        }

        viewModel.getScholarshipStartExamErrorMessage.observe(viewLifecycleOwner) {
            Log.d(TAG, "getQuizSingleQuestionsErrorMessage: ")
            binding?.textView58?.visibility = View.GONE
            //binding?.textView59?.visibility = View.GONE
            //binding?.totalMarkTextId?.visibility = View.GONE
            binding?.submitBtn?.visibility = View.GONE
            //binding?.noQuizQuestionAvailableId?.visibility = View.VISIBLE
           // binding?.noQuizQuestionAvailableId?.text = "Error loading the data"
        }



        viewModel.getScholarshipExamSubmitResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "getScholarshipExamSubmitResponse: ")
            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed(Runnable {
                binding?.loader?.visibility = View.GONE
                /*val bundle = Bundle()
                bundle.putString("result",it.data?.data?.result)
                bundle.putString("message",it.data?.data?.message)
                bundle.putString("score_text",it.data?.data?.scoreText)
                navigate(R.id.ScholarshipExamResultFragment,bundle)*/

                val action = it.data?.data?.result?.let { it1 ->
                    it.data?.data?.message?.let { it2 ->
                        it.data?.data?.scoreText?.let { it3 ->
                            enrollmentId?.let { it4 ->
                                examId?.let { it5 ->
                                    ScholarshipStartExamFragmentDirections.actionScholarshipStartExamFragmentToNavigationScholarshipExamList(
                                        it1, it2, it3, it4, it5
                                    )
                                }
                            }
                        }
                    }
                }
                action?.let { it1 -> findNavController().navigate(it1) }
            }, 2000)
        }

        viewModel.getScholarshipExamSubmitErrorMessage.observe(viewLifecycleOwner) {
            Log.d(TAG, "getScholarshipExamSubmitErrorMessage: ")
            binding?.loader?.visibility = View.GONE
        }

        binding?.submitBtn?.setOnClickListener {
            Log.d(TAG, "examApiCall submitBtn: ")
            submitExamResult()

        }

    }

    private fun submitExamResult() {
        Log.d(TAG, "examApiCall submitExamResult: examId  $examId")
        binding?.loader?.visibility = View.VISIBLE
        val mList = ArrayList<SubmitDatum>()
        if (mAdapter?.getList() != null) {
            for (item in mAdapter?.getList()!!) {
                if (item.optionsList != null) {
                    for (innerItem in item.optionsList!!) {
                        if (innerItem.mSelectedStatus) {
                            val data = SubmitDatum()
                            data.questionId = innerItem.mQuestionId
                            data.answer = innerItem.mQuestionString
                            mList.add(data)
                        }
                    }
                }

            }
        }

        viewModel.getScholarshipExamSubmit(ScholarshipExamSubmitRequest(null, examRowId?.toInt(), mList))
    }

    override fun onStop() {
        super.onStop()
        countDownTimer?.cancel()
        countDownTimer = null
    }

}