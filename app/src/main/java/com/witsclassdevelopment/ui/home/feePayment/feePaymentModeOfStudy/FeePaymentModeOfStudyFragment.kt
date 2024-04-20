package com.witsclassdevelopment.ui.home.feePayment.feePaymentModeOfStudy

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.witsclassdevelopment.R
import com.witsclassdevelopment.databinding.FragmentFeePaymentModeOfStudyBinding
import com.witsclassdevelopment.service.respose.homeBanner.feePayment.FeePaymentModeOfStudyResponse
import com.witsclassdevelopment.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeePaymentModeOfStudyFragment : BaseFragment(R.layout.fragment_fee_payment_mode_of_study),
    IModeOfPayment {

    companion object {
        const val TAG = "ModeOf"
    }

    private val viewModel: ModeOfPaymentViewModel by viewModels()
    private var binding: FragmentFeePaymentModeOfStudyBinding? = null
    private var mAdapter: FeePaymentModeOfPaymentAdapter?= null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFeePaymentModeOfStudyBinding.bind(view)

        viewModel.getModeOfPayment()
        binding?.progressBar?.visibility = View.VISIBLE
        mAdapter = FeePaymentModeOfPaymentAdapter(this)
        binding?.recyclerView?.adapter = mAdapter

        viewModel.modeOfPaymentResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "jobUpdatesResponse: size" + it.data?.data?.size)
            mAdapter?.updateList(it.data?.data)
            binding?.progressBar?.visibility = View.GONE
        }
        viewModel.errorMessage.observe(viewLifecycleOwner){
            Log.d(TAG, "errorMessage: ")
            binding?.progressBar?.visibility = View.GONE
        }
    }

    override fun onPause() {
        super.onPause()
        binding?.progressBar?.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        binding?.progressBar?.visibility = View.GONE
    }

    override fun clickEvent(item: FeePaymentModeOfStudyResponse.Datum?) {
        Log.d(TAG, "clickEvent: ")
        val bundle = Bundle()
        bundle.putString("mode_id",item?.id)
        bundle.putString("name",item?.name)
        navigate(R.id.study_center, bundle)
    }
}