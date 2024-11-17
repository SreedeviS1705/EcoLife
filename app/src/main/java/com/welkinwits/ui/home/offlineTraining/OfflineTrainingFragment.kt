package com.welkinwits.ui.home.offlineTraining

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.welkinwits.R
import com.welkinwits.databinding.FragmentOfflineTrainingBinding
import com.welkinwits.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OfflineTrainingFragment : BaseFragment(R.layout.fragment_offline_training) {

    private val viewModel: OfflineTrainingViewModel by activityViewModels()
    private var binding: FragmentOfflineTrainingBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}