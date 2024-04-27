package com.scorepsc.ui.home.analytics

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.scorepsc.R
import com.scorepsc.databinding.FragmentAnalyticsBinding
import com.scorepsc.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnalyticsFragment : BaseFragment(R.layout.fragment_analytics) {

    companion object {
        const val TAG = "AnalyticsFragment"
    }

    private val viewModel: AnalyticsViewModel by viewModels()
    private var binding: FragmentAnalyticsBinding? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAnalyticsBinding.bind(view)

        viewModel.getAnalytics()

        viewModel.analyticsResponse.observe(viewLifecycleOwner) {
            binding?.textView46?.text = it.data?.data?.videoClassesWatched.toString()
            binding?.textView43?.text = it.data?.data?.studyMaterialsWatched.toString()
            binding?.textView56?.text = it.data?.data?.quizAttendedPass.toString()
            binding?.textView66?.text = it.data?.data?.quizAttendedFailed.toString()
            binding?.textView76?.text = it.data?.data?.entranceAttentedPass.toString()
            binding?.totalEnteranceExamFailed?.text = it.data?.data?.entranceAttentedFailed.toString()
            binding?.generalExamPassText?.text = it.data?.data?.generalExamPass.toString()
            binding?.generalExamFailedText?.text = it.data?.data?.generalExamFailed.toString()
        }

        viewModel.analyticsErrorMessage.observe(viewLifecycleOwner) {
        }

    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

}