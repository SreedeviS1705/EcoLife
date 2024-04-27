package com.scorepsc.ui.home.scholarship.register

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.scorepsc.R
import com.scorepsc.databinding.FragmentScholarshipRegisterDetailsDisplayBinding
import com.scorepsc.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScholarshipRegisterDetailsDisplayFragment : BaseFragment(R.layout.fragment_scholarship_register_details_display) {

    companion object {
        const val TAG = "SRDisplayFragment"
    }

    private var binding: FragmentScholarshipRegisterDetailsDisplayBinding? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentScholarshipRegisterDetailsDisplayBinding.bind(view)

        val className = arguments?.getString("class")
        val school = arguments?.getString("school")
        val place = arguments?.getString("place")
        val mobile = arguments?.getString("mobile")
        val studId = arguments?.getString("studId")
        val mScholarshipName = arguments?.getString("ScholarshipName")
        val enrollmentId = arguments?.getString("enrollmentId")
        val examId = arguments?.getString("examId")
        binding?.textView21?.text = "Register for $mScholarshipName"
        binding?.studId?.text = "#$studId"
        binding?.classId?.text = className
        binding?.schoolId?.text = school
        binding?.placeId?.text = place
        binding?.mobileId?.text = mobile

        binding?.nextButton?.setOnClickListener {
            Log.d(TAG, "nextButton: ")
            val bundle = Bundle()
            bundle.putString("enrollmentId", enrollmentId)
            //navigate(R.id.scholarshipStartExamFragment, bundle)

            val action = enrollmentId?.let { it1 ->
                examId?.let { it2 ->
                    ScholarshipRegisterDetailsDisplayFragmentDirections.actionScholarshipRegisterDetailsDisplayFragmentToNavigationHomeFragment(
                        it1, it2
                    )
                }
            }
            action?.let { it1 -> findNavController().navigate(it1) }
        }

    }

}