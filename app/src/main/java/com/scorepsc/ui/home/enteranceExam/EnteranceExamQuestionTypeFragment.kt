package com.scorepsc.ui.home.enteranceExam

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.scorepsc.R
import com.scorepsc.databinding.FragmentEnteranceExamQuestionTypeBinding
import com.scorepsc.service.respose.homeBanner.enteranceExam.EnteranceExamQuestionTypeResponse
import com.scorepsc.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EnteranceExamQuestionTypeFragment : BaseFragment(R.layout.fragment_enterance_exam_question_type), IEntranceExamQuestionTypeListener {

    companion object {
        const val TAG = "EnteranceExamFragment"
    }

    private val viewModel: EnteranceExamQuestionTypeViewModel by viewModels()
    private var binding: FragmentEnteranceExamQuestionTypeBinding? = null
    private lateinit var mAdapter:EntranceExamQuestionTypeAdapter
    private var demoClass: String?= null


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEnteranceExamQuestionTypeBinding.bind(view)
        viewModel.getEnteranceQuestionTypeList()

        mAdapter = EntranceExamQuestionTypeAdapter(this)
        binding?.recyclerView?.adapter = mAdapter

        viewModel.enteranceExamQuestionTypeResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "examResponse: size" + it.data?.data?.size)
            if(it?.data?.data?.isEmpty() == true) {
                binding?.noDataAvailable?.visibility = View.VISIBLE
            } else {
                binding?.noDataAvailable?.visibility = View.GONE
            }
            mAdapter.updateList(it.data?.data)
        }

        viewModel.enteranceExamQuestionTypeErrorMessage.observe(viewLifecycleOwner) {
            binding?.noDataAvailable?.visibility = View.VISIBLE
            binding?.noDataAvailable?.text = "Error Occurred"
        }

        binding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d(TAG, "searchViewwwwwwwwwwwwww onQueryTextChange: ")
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d(TAG, "searchViewwwwwwwwwwwwww onQueryTextSubmit: "+query)
                query?.let { viewModel.searchEmteranceExam(it) }
                return true
            }
        })

        binding?.button3?.setOnClickListener {
            launchReport()
        }
    }

    override fun clickEvent(it1: EnteranceExamQuestionTypeResponse.Datum) {
        Log.d(TAG, "clickEventid: "+it1.id)
        val bundle = Bundle()
        bundle.putString("exam_id", it1.id)
        navigate(R.id.enteranceStartExamFragment, bundle)
    }

    override fun launchReport() {
        val action = EnteranceExamQuestionTypeFragmentDirections.actionEnteranceExamQuestionTypeFragmentToNavigationEnteranceReport()
        action?.let { it1 -> findNavController().navigate(it1) }
    }

    override fun launchAnswerKey(item: EnteranceExamQuestionTypeResponse.Datum?) {
        Log.d(TAG, "clickEventid: "+item)
        val bundle = Bundle()
        bundle.putString("exam_id", item?.id)
        bundle.putString("name", item?.name)
        navigate(R.id.answerKeyFragment, bundle)
    }
}