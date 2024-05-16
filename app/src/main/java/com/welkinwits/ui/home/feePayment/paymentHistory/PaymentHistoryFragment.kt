package com.welkinwits.ui.home.feePayment.paymentHistory

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.welkinwits.R
import com.welkinwits.databinding.FragmentPaymentHistoryBinding
import com.welkinwits.service.respose.homeBanner.paymentHistory.GetPaymentHistoryResponse
import com.welkinwits.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentHistoryFragment : BaseFragment(R.layout.fragment_payment_history) {

    companion object {
        const val TAG = "PaymentHistoryFragment"
    }

    private val viewModel: PaymentHistoryViewModel by viewModels()
    private var binding: FragmentPaymentHistoryBinding? = null
    private var mAdapter: PaymentHistoryAdapter?= null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPaymentHistoryBinding.bind(view)

        viewModel.getPayMentHistory()
        binding?.progressBarContainer?.visibility = View.VISIBLE
        mAdapter = PaymentHistoryAdapter()
        binding?.recyclerView?.adapter = mAdapter

        viewModel.getPaymentHistoryResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "getPaymentHistoryResponse: size" + it.data?.data?.size)
            val historyList:ArrayList<GetPaymentHistoryResponse.Datum>? = ArrayList()
            if (historyList != null) {
                for(item in it.data?.data!!) {
                    if(!item.orderStatus.equals("PENDING")) {
                        historyList.add(item)
                    }
                }
            }
            mAdapter?.updateList(historyList)
            binding?.progressBarContainer?.visibility = View.GONE
        }
        viewModel.getPaymentHistoryerrorMessage.observe(viewLifecycleOwner){
            Log.d(TAG, "getPaymentHistoryerrorMessage: ")
            binding?.progressBarContainer?.visibility = View.GONE
        }
    }

    override fun onPause() {
        super.onPause()
        binding?.progressBarContainer?.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        binding?.progressBarContainer?.visibility = View.GONE
    }
}