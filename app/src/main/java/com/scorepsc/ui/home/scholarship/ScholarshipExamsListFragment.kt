package com.scorepsc.ui.home.scholarship

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.scorepsc.R
import com.scorepsc.databinding.FragmentScholarshipsListBinding
import com.scorepsc.service.respose.homeBanner.scholarship.ScholarshipExamsResponse
import com.scorepsc.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScholarshipExamsListFragment : BaseFragment(R.layout.fragment_scholarships_list), IScholarshipClickEvent {

    companion object {
        const val TAG = "ScholarshipFragment"
    }
    private var binding: FragmentScholarshipsListBinding? = null
    private val viewModel: ScholarshipExamsListViewModel by viewModels()

    private lateinit var mAdapter: ScholarshipExamsListAdapter
    private var mParticipateButtonClicked = false
    private var mScholarshipName: String?= null


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentScholarshipsListBinding.bind(view)

        mAdapter = activity?.let { ScholarshipExamsListAdapter(it, this) }!!
        binding?.recyclerView?.adapter = mAdapter

        viewModel.getScholarshipExamsList()
        binding?.progressBarId?.visibility = View.VISIBLE

        viewModel.getScholarshipExamsResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "getScholarshipExamsResponse size: "+it.data?.data?.size)
            binding?.progressBarId?.visibility = View.GONE
            if(it.data?.data?.isEmpty() == true) {
                binding?.noListAvailableId?.visibility = View.VISIBLE
                binding?.noListAvailableId?.text = "No scholarship available"
            } else {
                binding?.noListAvailableId?.visibility = View.GONE
            }

            it.data?.data?.let { it1 -> mAdapter.updateList(it1) }
        }

        viewModel.getScholarshipExamsErrorMessage.observe(viewLifecycleOwner) {
            Log.d(TAG, "getScholarshipExamsErrorMessage: ")
            binding?.progressBarId?.visibility = View.GONE
            binding?.noListAvailableId?.visibility = View.VISIBLE
            binding?.noListAvailableId?.text = "Error loading the data"
        }

        viewModel.getScholarshipCreateExamResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "getScholarshipCreateExamResponse size: "+it.data)
            binding?.progressBarId?.visibility = View.GONE
            if(!mParticipateButtonClicked) {
                val bundle = Bundle()
                bundle.putString("enrollment_id", it.data?.data?.id)
                bundle.putString("examId", it.data?.data?.examId)
                bundle.putString("ScholarshipName", mScholarshipName)
                navigate(R.id.scholarshipRegisterFragment, bundle)
            }
        }

        viewModel.getScholarshipCreateExamErrorMessage.observe(viewLifecycleOwner) {
            Log.d(TAG, "getScholarshipCreateExamErrorMessage: ")
            binding?.progressBarId?.visibility = View.GONE
            Toast.makeText(activity,"Error occurred",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
        mParticipateButtonClicked = true
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun clickEvent(item: ScholarshipExamsResponse.Datum?) {
        Log.d(TAG, "clickEvent: ")
        if (item != null) {
            mScholarshipName = item.name
            mParticipateButtonClicked = false
            Log.d(TAG, "clickEvent: "+item.id)
            binding?.progressBarId?.visibility = View.VISIBLE
            viewModel.getScholarshipCreateExam(item.id)
        }
    }

    override fun clickPolicy(item: ScholarshipExamsResponse.Datum?) {
        Log.d(TAG, "clickPolicy: ")
        activity?.let { showAlertDialogButtonClicked(it,item) }
    }

    private fun showAlertDialogButtonClicked(
        mContext: Context,
        item: ScholarshipExamsResponse.Datum?
    ) {
        val dialog = Dialog(mContext)
        dialog.setContentView(R.layout.scholarship_policy_des_dialogue)

        val closeButton = dialog.findViewById(R.id.imageView43) as ImageView
        val scholarshipDescriptionId = dialog.findViewById(R.id.scholarshipDescriptionId) as TextView
        scholarshipDescriptionId.text = item?.description
        closeButton.setOnClickListener {
            dialog.hide()
        }


        dialog.show()
    }
}