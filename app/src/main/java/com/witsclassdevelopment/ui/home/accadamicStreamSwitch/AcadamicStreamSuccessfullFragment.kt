package com.witsclassdevelopment.ui.home.accadamicStreamSwitch

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.witsclassdevelopment.R
import com.witsclassdevelopment.databinding.FragmentAcadamicStreamSuccessScreanBinding
import com.witsclassdevelopment.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AcadamicStreamSuccessfullFragment : BaseFragment(R.layout.fragment_acadamic_stream_success_screan) {

    companion object {
        const val TAG = "AcadamicStream"
    }

    private var binding: FragmentAcadamicStreamSuccessScreanBinding? = null


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAcadamicStreamSuccessScreanBinding.bind(view)

        binding?.exploreBtn?.setOnClickListener {
            var action = AcadamicStreamSuccessfullFragmentDirections.actionAcademicLevelsFragmentToHome()
            action?.let { it1 -> findNavController().navigate(it1) }
        }


    }

    /*    override fun clickEvent(data: SubjectResponse.SubjectData) {
            Log.d(TAG, "clickEvent: ")
            val data = Bundle().apply {
                putString("subID", data.subjectId)
                putString("subName", data.subjectName)
                putString("redirectType",redirectType)
            }
            navigate(R.id.chapterFragment, data)
        }*/
}