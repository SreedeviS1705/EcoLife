package com.scorepsc.ui.home.feePayment.feePaymentModeOfStudy.modeOfPayment.feeStructure

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.view.View
import com.scorepsc.R
import com.scorepsc.databinding.FragmentPaymentFailedBinding
import com.scorepsc.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class PaymentFailedFragment : BaseFragment(R.layout.fragment_payment_failed) {

    companion object {
        const val TAG = "PaymentFailedFragment"
    }

    //private val viewModel: PaymentInitViewModel by viewModels()
    private var binding: FragmentPaymentFailedBinding? = null


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPaymentFailedBinding.bind(view)

        val text = "<font>To track your payment</font> <font color=#4E74F9>check payment history</font>"
        binding?.failedText?.text = Html.fromHtml(text)

        binding?.failedText?.setOnClickListener {
            navigate(R.id.payment_history)
        }
    }

}