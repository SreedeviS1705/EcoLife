package com.welkinwits.ui.home.feePayment.feePaymentModeOfStudy.modeOfPayment.feeStructure.partPayment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.welkinwits.R
import com.welkinwits.databinding.FragmentPartPaymentBinding
import com.welkinwits.service.respose.homeBanner.partPayment.PartPaymentListResponse
import com.welkinwits.ui.base.BaseFragment
import com.welkinwits.ui.home.feePayment.feePaymentModeOfStudy.modeOfPayment.StudyCenterFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PartPaymentListFragment : BaseFragment(R.layout.fragment_part_payment), IPartPaymentFragment {
    private var duration: String?= null
    companion object {
        const val TAG = "PartPaymentListFragment"
    }

    private var mAdapter: PartPaymentListAdapter?= null
    private val viewModel: PartPaymentListViewModel by viewModels()
    private var binding: FragmentPartPaymentBinding? = null
    private var enrollmentId: String?= null
    private var packageId: String?= null


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPartPaymentBinding.bind(view)
        packageId = arguments?.getString("packageId")
        enrollmentId = arguments?.getString("enrollmentId")
        duration = arguments?.getString("duration")
        val type: String? = arguments?.getString("type")

        packageId?.let {
            if(type == "byPackage") {
                viewModel.getPartPaymentPackageList(packageId, enrollmentId)
            } else {
                viewModel.getPartPaymentList(packageId)
            }

            showProgressBar(View.VISIBLE)
        }

        mAdapter = PartPaymentListAdapter(this)
        binding?.partPaymentRecyclerView?.adapter = mAdapter

        viewModel.partPaymentListResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "partPaymentListResponse: size: " + it.data?.data?.size)
            mAdapter?.updateList(it.data?.data)
            showProgressBar(View.GONE)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner){
            Log.d(TAG, "errorMessage: ")
            showProgressBar(View.GONE)
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(StudyCenterFragment.TAG, "onStart: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(StudyCenterFragment.TAG, "onPause: ")
        showProgressBar(View.GONE)
    }

    override fun onStop() {
        super.onStop()
        Log.d(StudyCenterFragment.TAG, "onStop: ")
        showProgressBar(View.GONE)
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

    override fun clickEvent(item: PartPaymentListResponse.Datum) {
        Log.d(TAG, "clickEvent: ")
        val bundle = Bundle()
        bundle.putString("offerAmount", item.amount)
        bundle.putString("packageName", item.title)
        bundle.putString("partPaymentId", item.id)
        bundle.putString("duration", duration)
        bundle.putString("enrollmentId", enrollmentId)
        bundle.putString("packageId", packageId)
        navigate(R.id.part_payment_order_bill, bundle)
    }


}