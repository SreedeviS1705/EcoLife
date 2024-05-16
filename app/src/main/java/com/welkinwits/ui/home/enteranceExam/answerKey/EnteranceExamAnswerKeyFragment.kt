package com.welkinwits.ui.home.enteranceExam.answerKey

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.welkinwits.R
import com.welkinwits.databinding.FragmentEnteranceExamAnswerKeyBinding
import com.welkinwits.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EnteranceExamAnswerKeyFragment : BaseFragment(R.layout.fragment_enterance_exam_answer_key) {

    companion object {
        const val TAG = "EEKeyFragment"
    }

    private val viewModel: EnteranceExamAnswerKeyViewModel by viewModels()
    private var binding: FragmentEnteranceExamAnswerKeyBinding? = null
    private lateinit var mAdapter:EnteranceAnswerKeyAdapter
    //private var demoClass: String?= null


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEnteranceExamAnswerKeyBinding.bind(view)
        binding?.progressBarId?.visibility = View.VISIBLE
        val examId = arguments?.getString("exam_id")
        val name = arguments?.getString("name")
        examId?.let {
            viewModel.getEnteranceQuestionTypeList(examId.toInt())
        }

        mAdapter = EnteranceAnswerKeyAdapter(this.context)
        binding?.recyclerView?.adapter = mAdapter

        viewModel.enteranceExamAnswerKeyResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "enteranceExamAnswerKeyResponse: size" + it.data?.data?.size)
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                binding?.textView102?.text = name
                binding?.progressBarId?.visibility = View.GONE
                if (it?.data?.data?.isEmpty() == true) {
                    binding?.noDataAvailable?.visibility = View.VISIBLE
                } else {
                    binding?.noDataAvailable?.visibility = View.GONE
                    mAdapter.updateList(it.data?.data)
                }
            }, 1000)
        }

        viewModel.enteranceExamAnswerKeyErrorMessage.observe(viewLifecycleOwner) {
            binding?.progressBarId?.visibility = View.GONE
            binding?.noDataAvailable?.visibility = View.VISIBLE
            binding?.noDataAvailable?.text = "Error Occurred"
        }

    }

/*    override fun clickEvent(it1: EnteranceExamQuestionTypeResponse.Datum) {
        Log.d(TAG, "clickEventid: "+it1.id)
        val bundle = Bundle()
        bundle.putString("exam_id", it1.id)
        navigate(R.id.enteranceStartExamFragment, bundle)
    }

    override fun launchReport() {
        val action = EnteranceExamQuestionTypeFragmentDirections.actionEnteranceExamQuestionTypeFragmentToNavigationEnteranceReport()
        action?.let { it1 -> findNavController().navigate(it1) }
    }*/

    override fun onPause() {
        super.onPause()
        binding?.progressBarId?.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        binding?.progressBarId?.visibility = View.GONE
    }
}