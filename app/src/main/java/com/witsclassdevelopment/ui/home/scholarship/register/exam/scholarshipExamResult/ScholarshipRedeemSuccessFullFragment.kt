package com.witsclassdevelopment.ui.home.scholarship.register.exam.scholarshipExamResult

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.witsclassdevelopment.R
import com.witsclassdevelopment.databinding.FragmentScholarshipRedeemSuccessfullBinding
import com.witsclassdevelopment.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ScholarshipRedeemSuccessFullFragment : BaseFragment(R.layout.fragment_scholarship_redeem_successfull) {

    companion object {
        const val TAG = "SRedeemSlFragment"
    }
    private var binding: FragmentScholarshipRedeemSuccessfullBinding? = null


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentScholarshipRedeemSuccessfullBinding.bind(view)

        binding?.backToHomeId?.setOnClickListener {
            navigate(R.id.navigation_home)
        }

        binding?.redeemLinkId?.setOnClickListener {
            //Open Web URL
        }

    }

}