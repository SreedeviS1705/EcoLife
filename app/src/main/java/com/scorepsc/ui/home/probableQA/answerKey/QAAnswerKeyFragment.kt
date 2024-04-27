package com.scorepsc.ui.home.probableQA.answerKey

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.scorepsc.R
import com.scorepsc.databinding.FragmentQaAnswerKeyBinding
import com.scorepsc.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class QAAnswerKeyFragment : BaseFragment(R.layout.fragment_qa_answer_key) {

    companion object {
        const val TAG = "QAAnswerKeyFragment"
    }

    private val viewModel: QAAnswerKeyViewModel by viewModels()
    private var binding: FragmentQaAnswerKeyBinding? = null
    private lateinit var mAdapter:QAAnswerKeyAdapter
    //private var demoClass: String?= null


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentQaAnswerKeyBinding.bind(view)
        binding?.progressBarId?.visibility = View.VISIBLE
        val id = arguments?.getString("id")
        id?.let {
            viewModel.getQAAnswerKey(id.toInt())
        }

        mAdapter = QAAnswerKeyAdapter(this.context)
        binding?.recyclerView?.adapter = mAdapter

        viewModel.qaAnswerKeyResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "enteranceExamAnswerKeyResponse: size" + it.data?.data?.size)
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                binding?.progressBarId?.visibility = View.GONE
                if (it?.data?.data?.isEmpty() == true) {
                    binding?.noDataAvailable?.visibility = View.VISIBLE
                } else {
                    binding?.noDataAvailable?.visibility = View.GONE
                    mAdapter.updateList(it.data?.data)
                }
            }, 1000)
        }

        viewModel.qaAnswerKeyErrorMessage.observe(viewLifecycleOwner) {
            binding?.progressBarId?.visibility = View.GONE
            binding?.noDataAvailable?.visibility = View.VISIBLE
            binding?.noDataAvailable?.text = "Error Occurred"
        }

    }

    override fun onPause() {
        super.onPause()
        binding?.progressBarId?.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        binding?.progressBarId?.visibility = View.GONE
    }
}