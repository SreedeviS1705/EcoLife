package com.welkinwits.ui.home.feePayment.feePaymentModeOfStudy.modeOfPayment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.welkinwits.R
import com.welkinwits.databinding.FragmentStudyCenterBinding
import com.welkinwits.service.respose.homeBanner.studyCenter.StudyCenterResponse
import com.welkinwits.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudyCenterFragment : BaseFragment(R.layout.fragment_study_center) {

    companion object {
        const val TAG = "StudyCenterFragment"
    }

    private val viewModel: StudyCenterViewModel by viewModels()
    private var binding: FragmentStudyCenterBinding? = null
    private var locationAdapter: ArrayAdapter<String>? = null
    private var studCenterArrayList:ArrayList<StudyCenterResponse.Datum> ?= null
    private var franchisesId:Int ?= null
    private var blockApiCall = true
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
        binding = FragmentStudyCenterBinding.bind(view)
        val modeId = arguments?.getString("mode_id")
        val name = arguments?.getString("name")
        viewModel.getStudyCenter()
        showProgressBar(View.VISIBLE)

        viewModel.modeOfPaymentResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "modeOfPaymentResponse: size" + it.data?.data?.size)
            showProgressBar(View.GONE)
            franchisesId = it.data?.data?.get(0)?.id?.let { it1 -> Integer.parseInt(it1) }
            studCenterArrayList = it.data?.data
            binding?.address?.text = it.data?.data?.get(0)?.address+","+it.data?.data?.get(0)?.district+","+it.data?.data?.get(0)?.state
            binding?.mobileNumber?.text = it.data?.data?.get(0)?.mobile
            binding?.emailId?.text = it.data?.data?.get(0)?.email


            val locationArray = ArrayList<String>()
            for (i in 0..(it.data?.data?.size!! -1)) {
                it.data.data.get(i).name?.let { it1 -> locationArray.add(it1) }
            }
            locationAdapter =
                this.context?.let { ArrayAdapter(it,android.R.layout.simple_spinner_item, locationArray.toList()) }
            locationAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding?.studyCenterSpinner?.adapter = locationAdapter
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            showProgressBar(View.GONE)
        }

        binding?.studyCenterSpinner?.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Log.d(TAG, "onItemSelected: $p2")
                binding?.address?.text = studCenterArrayList?.get(p2)?.address+","+studCenterArrayList?.get(0)?.district+","+studCenterArrayList?.get(0)?.state
                binding?.mobileNumber?.text = studCenterArrayList?.get(p2)?.mobile
                binding?.emailId?.text = studCenterArrayList?.get(p2)?.email
                franchisesId = studCenterArrayList?.get(p2)?.id?.let { Integer.parseInt(it) }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.d(TAG, "onNothingSelected: $p0")
            }
        }

        viewModel.verifyProceedEnrollmentResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "verifyProceedEnrollmentResponse: ")
            if(!blockApiCall) {
                val bundle = Bundle()
                bundle.putString("name", name)
                bundle.putString("modeId", modeId)
                bundle.putString("enrollmentId", it.data?.data?.enrollmentId.toString())
                navigate(R.id.fee_structure, bundle)
                showProgressBar(View.GONE)
            }
        }

        viewModel.verifyProceedEnrollmenterrorMessage.observe(viewLifecycleOwner) {
            Log.d(TAG, "verifyProceedEnrollmenterrorMessage: ")
            showProgressBar(View.GONE)
        }

        binding?.verifyProcedBtn?.setOnClickListener {
            showProgressBar(View.VISIBLE)
            viewModel.getVerifyProceedEnrollment(modeId, franchisesId)
            blockApiCall = false
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
        showProgressBar(View.GONE)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
        showProgressBar(View.GONE)
        blockApiCall = true
    }

    private fun showProgressBar(visible: Int) {
        if(visible == View.GONE) {
            binding?.containerId?.visibility = View.VISIBLE
            binding?.progressBarId?.visibility = View.GONE
        } else {
            binding?.containerId?.visibility = View.GONE
            binding?.progressBarId?.visibility = View.VISIBLE
        }
    }

}