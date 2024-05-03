package com.scorepsc.ui.home.faq

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.scorepsc.R
import com.scorepsc.databinding.FragmentFaqBinding
import com.scorepsc.databinding.FragmentHelpBinding
import com.scorepsc.ui.base.BaseFragment
import com.scorepsc.ui.help.HelpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FaqFragment : BaseFragment(R.layout.fragment_faq) {

    private val faqViewModel: FaqViewModel by viewModels()
    private var binding: FragmentFaqBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFaqBinding.bind(view)

        binding?.faqWebView?.loadUrl("https://scoreexamsolution.com/faq.html")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding=null
    }

}