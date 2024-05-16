package com.welkinwits.ui.home.feePayment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.welkinwits.R
import com.welkinwits.databinding.FragmentFeePaymentBinding
import com.welkinwits.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeePaymentFragment : BaseFragment(R.layout.fragment_fee_payment){

    companion object {
        const val TAG = "FeePaymentFragment"
    }

    private val viewModel: FeePaymentViewModel by viewModels()
    private var binding: FragmentFeePaymentBinding? = null
    private var subscriptionStatus:String ?= ""
    private var endDate:String ?= ""

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFeePaymentBinding.bind(view)
        viewModel.getActiveSubscription()
        binding?.cardView2?.setOnClickListener {
            if(subscriptionStatus?.compareTo("ACTIVE") == 0) {
                val bundle = Bundle()
                bundle.putString("endDate",endDate)
                navigate(R.id.activeSubscriptionFragment,bundle)
            } else {
                navigate(R.id.fee_payment_mode_of_payment)
            }
        }

        binding?.paymentHistoryItem?.setOnClickListener {
            navigate(R.id.payment_history)
        }

        binding?.constraintLayout15?.setOnClickListener {
            navigate(R.id.packageListingPartPay)
        }

        viewModel.getActiveSubscriptionResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "getActiveSubscriptionResponse:")
            subscriptionStatus = it.data?.data?.subscriptionStatus
            endDate = it.data?.data?.endDate

        }
        viewModel.getActiveSubscriptionerrorMessage.observe(viewLifecycleOwner){
            Log.d(TAG, "getActiveSubscriptionerrorMessage: ")
        }

    }
}