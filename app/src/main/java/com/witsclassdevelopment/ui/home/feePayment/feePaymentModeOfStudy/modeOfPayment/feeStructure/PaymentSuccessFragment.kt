package com.witsclassdevelopment.ui.home.feePayment.feePaymentModeOfStudy.modeOfPayment.feeStructure

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.view.View
import com.witsclassdevelopment.R
import com.witsclassdevelopment.databinding.FragmentPaymentSuccessBinding
import com.witsclassdevelopment.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class PaymentSuccessFragment : BaseFragment(R.layout.fragment_payment_success) {

    companion object {
        const val TAG = "PaymentSuccessFragment"
    }

    //private val viewModel: PaymentInitViewModel by viewModels()
    private var binding: FragmentPaymentSuccessBinding? = null


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPaymentSuccessBinding.bind(view)

        val text = "<font>To verify your payment </font> <font color=#4E74F9>check payment history</font>"
        binding?.successText?.text = Html.fromHtml(text)

        binding?.successText?.setOnClickListener {
            navigate(R.id.payment_history)
        }
    }

}