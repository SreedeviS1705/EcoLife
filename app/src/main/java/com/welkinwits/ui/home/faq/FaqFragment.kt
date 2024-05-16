package com.welkinwits.ui.home.faq

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.welkinwits.R
import com.welkinwits.databinding.FragmentFaqBinding
import com.welkinwits.ui.base.BaseFragment
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