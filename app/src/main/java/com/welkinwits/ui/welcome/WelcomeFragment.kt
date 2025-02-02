package com.welkinwits.ui.welcome

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import com.welkinwits.R
import com.welkinwits.databinding.FragmentWelcomeBinding
import com.welkinwits.ui.base.BaseFragment


class WelcomeFragment : BaseFragment(R.layout.fragment_welcome) {

    private var binding: FragmentWelcomeBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWelcomeBinding.bind(view)
        binding?.createAccount?.paintFlags =
            binding?.createAccount?.paintFlags!! or Paint.UNDERLINE_TEXT_FLAG

        binding?.signIn?.setOnClickListener {
            navigate(R.id.signInFragment)
        }
        binding?.createAccount?.setOnClickListener {
            navigate(R.id.generalDetailFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}