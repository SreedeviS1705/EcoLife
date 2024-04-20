package com.witsclassdevelopment.ui.home.feePayment.feePaymentModeOfStudy.modeOfPayment.feeStructure

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.witsclassdevelopment.R
import com.witsclassdevelopment.databinding.FragmentFeeStructureBinding
import com.witsclassdevelopment.service.respose.homeBanner.feeStructure.FeeStructureResponse
import com.witsclassdevelopment.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeeStructureFragment : BaseFragment(R.layout.fragment_fee_structure), IFeeStructureFragment {

    companion object {
        const val TAG = "FeeStructureFragment"
    }

    private var mAdapter: FeeStructureAdapter?= null
    private val viewModel: FeeStructureViewModel by viewModels()
    private var binding: FragmentFeeStructureBinding? = null
    private var enrollmentId: String?= null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFeeStructureBinding.bind(view)
        val name = arguments?.getString("name")
        val modeId = arguments?.getString("modeId")
        enrollmentId = arguments?.getString("enrollmentId")
        binding?.textView23?.text = "Fee Structure Based On $name Class"
        viewModel.getFeeStructure(modeId)
        showProgressBar(View.VISIBLE)

        mAdapter = FeeStructureAdapter(this)
        binding?.feeStructureRecyclerView?.adapter = mAdapter

        viewModel.feeStructureResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "FeeStructureFragment: size: " + it.data?.data?.size)
            if(it.data?.data?.isNotEmpty() == true) {
                mAdapter?.updateList(it.data?.data)
            }
            showProgressBar(View.GONE)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            showProgressBar(View.GONE)
        }

    }

    override fun clickEvent(item: FeeStructureResponse.Datum) {
        Log.d(TAG, "clickEvent: ")
        val bundle = Bundle()
        bundle.putString("offerAmount", item.offerAmount)
        bundle.putString("packageName", item.packageName)
        bundle.putString("duration", item.tenure)
        bundle.putString("packageId", item.id)
        bundle.putString("enrollmentId", enrollmentId)
        navigate(R.id.payment_order_bill, bundle)
    }

    override fun clickPartPayment(datum: FeeStructureResponse.Datum) {
        Log.d(TAG, "clickPartPayment: ")
        val bundle = Bundle()
        bundle.putString("packageId",datum.id)
        bundle.putString("duration",datum.tenure)
        bundle.putString("enrollmentId", enrollmentId)
        bundle.putString("type", "normal")
        navigate(R.id.part_payment_list, bundle)
    }

    override fun onPause() {
        super.onPause()
        showProgressBar(View.GONE)
    }

    override fun onStop() {
        super.onStop()
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

}