package com.scorepsc.ui.home.feePayment.getActiveSubscription

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.view.View
import com.scorepsc.R
import com.scorepsc.databinding.FragmentActiveSubscriptionBinding
import com.scorepsc.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActiveSubscriptionFragment : BaseFragment(R.layout.fragment_active_subscription) {

    companion object {
        const val TAG = "ActiveSubscriptionFragment"
    }

    private var binding: FragmentActiveSubscriptionBinding? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentActiveSubscriptionBinding.bind(view)

        arguments?.let {
            val endDate = it.get("endDate")
            val text = "Your subscription will expire on <br><b><font color=#F94E4E>$endDate</font></b>"
            binding?.textView48?.text = Html.fromHtml(text)

        }

    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }
}