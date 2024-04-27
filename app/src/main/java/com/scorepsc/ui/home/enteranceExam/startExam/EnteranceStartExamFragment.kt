package com.scorepsc.ui.home.enteranceExam.startExam

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
import com.scorepsc.databinding.FragmentEnteranceStartExamBinding
import com.scorepsc.model.EnteranceExamQuestionModelData
import com.scorepsc.service.request.enteranceExam.EnteranceExamSubmitRequest
import com.scorepsc.service.request.enteranceExam.EnteranceSubmitDatum
import com.scorepsc.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class EnteranceStartExamFragment : BaseFragment(R.layout.fragment_enterance_start_exam) {

    companion object {
        const val TAG = "EnteranceSE"
    }
    private var binding: FragmentEnteranceStartExamBinding? = null
    private val viewModel: EnteranceStartExamViewModel by viewModels()
    private lateinit var mAdapter: EnteranceStartExamAdapter
    private var  countDownTimer: CountDownTimer?= null
    private var examId: String?= null
    private var enrollmentId:String = ""


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEnteranceStartExamBinding.bind(view)
        examId = arguments?.getString("exam_id")
        Log.d(TAG, "examId: "+examId)

        examId?.let { examId?.toInt()?.let { it1 -> viewModel.getEnteranceStartExam(it1) } }
        mAdapter = activity?.let { EnteranceStartExamAdapter(it) }!!
        binding?.listRecyclerview?.adapter = mAdapter

        viewModel.enteranceExamStartExamResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "enteranceExamStartExamResponse size: "+it.data?.data?.questions?.size)
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
                enrollmentId = it.data?.data?.exam?.enrollmentId.toString()
                binding?.noListAvailableId?.visibility = View.GONE
                binding?.textView58?.visibility = View.VISIBLE
                binding?.submitBtn?.visibility = View.VISIBLE
                binding?.textView74?.visibility = View.VISIBLE
                binding?.textView58?.text = it?.data?.data?.exam?.name

                val questions = it.data?.data?.questions
                for (item in questions!!) {
                    val optionsList: ArrayList<EnteranceExamQuestionModelData>? = ArrayList<EnteranceExamQuestionModelData>()
                    for (innerItem in item.options!!) {
                        val model = EnteranceExamQuestionModelData(false, innerItem, item?.id?.toInt(),true)
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

        viewModel.enteranceExamStartExamErrorMessage.observe(viewLifecycleOwner) {
           Log.d(TAG, "enteranceExamStartExamErrorMessage: ")
            binding?.textView58?.visibility = View.GONE
            //binding?.textView59?.visibility = View.GONE
            //binding?.totalMarkTextId?.visibility = View.GONE
            binding?.submitBtn?.visibility = View.GONE
            //binding?.noQuizQuestionAvailableId?.visibility = View.VISIBLE
           // binding?.noQuizQuestionAvailableId?.text = "Error loading the data"
        }


        viewModel.getEnteranceExamSubmitResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "getScholarshipExamSubmitResponse: ")
            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed(Runnable {
                binding?.loader?.visibility = View.GONE
/*                val bundle = Bundle()
                bundle.putString("result",it.data?.data?.result)
                bundle.putString("message",it.data?.data?.message)
                bundle.putString("score_text",it.data?.data?.scoreText)
                navigate(R.id.ScholarshipExamResultFragment,bundle)*/

                val action = it.data?.data?.result?.let { it1 ->
                    it.data?.data?.message?.let { it2 ->
                        it.data?.data?.scoreText?.let { it3 ->
                            enrollmentId?.let { it4 ->
                                examId?.let { it5 ->
                                    EnteranceStartExamFragmentDirections.actionEnteranceStartExamFragmentToNavigationEnteranceExamList(
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

        viewModel.getEnteranceExamSubmitErrorMessage.observe(viewLifecycleOwner) {
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
        val mList = ArrayList<EnteranceSubmitDatum>()
        if (mAdapter?.getList() != null) {
            for (item in mAdapter?.getList()!!) {
                if (item.optionsList != null) {
                    for (innerItem in item.optionsList!!) {
                        if (innerItem.mSelectedStatus) {
                            val data = EnteranceSubmitDatum()
                            data.questionId = innerItem.mQuestionId
                            data.optionId = innerItem.mQuestion.id?.toInt()
                            mList.add(data)
                        }
                    }
                }

            }
        }

        if(mList.size <3) {
            Toast.makeText(requireContext(), "Please attempt minimum 3 Questions", Toast.LENGTH_LONG).show()
        } else {
            binding?.loader?.visibility = View.VISIBLE
            Log.d(TAG, "submitExamResult: mList: "+ mList.toString())
            viewModel.getEnteranceExamSubmit(EnteranceExamSubmitRequest(null, enrollmentId?.toInt(), mList))
        }

    }

    /*override fun onStop() {
        super.onStop()
        countDownTimer?.cancel()
        countDownTimer = null
    }*/

}