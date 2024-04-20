package com.witsclassdevelopment.ui.account

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.witsclassdevelopment.R
import com.witsclassdevelopment.databinding.FragmentAccountBinding
import com.witsclassdevelopment.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AccountFragment : BaseFragment(R.layout.fragment_account) {

    private var binding: FragmentAccountBinding? = null


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAccountBinding.bind(view)

        binding?.textView4?.setOnClickListener {
            navigate(R.id.navigation_profile)
        }

        binding?.textView5?.setOnClickListener {
            navigate(R.id.payment_history)
        }

        binding?.textView7?.setOnClickListener {

        }

        binding?.termsConditions?.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://arown.in/terms-and-conditions/"))
            startActivity(browserIntent)
        }

        binding?.textView10?.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://arown.in/privacy-policy/"))
            startActivity(browserIntent)
        }

        binding?.textView9?.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://arown.in/refund-and-cancellation-policy/"))
            startActivity(browserIntent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}