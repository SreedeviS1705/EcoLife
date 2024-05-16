package com.welkinwits.ui.home.scholarship.register

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.welkinwits.R
import com.welkinwits.databinding.FragmentScholarshipRegisterBinding
import com.welkinwits.service.request.ScholarshipUpdateEnrollmentRequest
import com.welkinwits.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScholarshipRegisterFragment : BaseFragment(R.layout.fragment_scholarship_register) {

    companion object {
        const val TAG = "SRFragment"
    }

    private var binding: FragmentScholarshipRegisterBinding? = null
    private val viewModel: ScholarshipRegisterViewModel by viewModels()
    private var mButtonClicked = false

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentScholarshipRegisterBinding.bind(view)

        val enrollmentId = arguments?.getString("enrollment_id")
        val examId = arguments?.getString("examId")
        val mScholarshipName = arguments?.getString("ScholarshipName")
        binding?.textView21?.text = "Register for $mScholarshipName"


        viewModel.getScholarshipUpdateEnrollmentResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "getScholarshipUpdateEnrollmentResponse size: ")
            binding?.progressBarId?.visibility = View.GONE
            if(!mButtonClicked) {
                Log.d(TAG, "getScholarshipUpdateEnrollmentResponse mButtonClicked $mButtonClicked")
                if(it.data?.message?.equals("UPDATE CreateScholarshipEnrollment") == true) {
                    val bundle = Bundle()
                    bundle.putString("studId",it.data.data.studId)
                    bundle.putString("class",it.data.data.class_)
                    bundle.putString("school",it.data.data.school)
                    bundle.putString("place",it.data.data.place)
                    bundle.putString("mobile",it.data.data.mobile)
                    bundle.putString("ScholarshipName",mScholarshipName)
                    bundle.putString("enrollmentId",enrollmentId)
                    bundle.putString("examId",examId)
                    navigate(R.id.scholarshipRegisterDetailsDisplayFragment, bundle)
                }
            }

        }

        viewModel.getScholarshipUpdateEnrollmentErrorMessage.observe(viewLifecycleOwner) {
            Log.d(TAG, "getScholarshipExamsErrorMessage: ")
            binding?.progressBarId?.visibility = View.GONE
        }

        binding?.nextButton?.setOnClickListener {
            Log.d(TAG, "nextButton: ")
            var validationCheck = false
            if (binding?.classId?.text.toString().isEmpty()) {
                binding?.classId?.error = "Please enter class name"
                validationCheck = true
            }
            if (binding?.schoolId?.text.toString().isEmpty()) {
                binding?.schoolId?.error = "Please enter school name"
                validationCheck = true
            }

            if (binding?.placeId?.text.toString().isEmpty()) {
                binding?.placeId?.error = "Please enter place name"
                validationCheck = true
            }

            if (binding?.contactNumberId?.text.toString().isEmpty()) {
                binding?.contactNumberId?.error = "Please enter contact number"
                validationCheck = true
            }

            if (!validationCheck) {
                mButtonClicked = false
                binding?.progressBarId?.visibility = View.VISIBLE
                viewModel.getScholarshipUpdateEnrollment(
                    ScholarshipUpdateEnrollmentRequest(
                        null,
                        enrollmentId,
                        binding?.classId?.text.toString(),
                        binding?.schoolId?.text.toString(),
                        binding?.placeId?.text?.toString(),
                        binding?.contactNumberId?.text.toString()
                    )
                )
            } else {
                Log.d(TAG, "validationCheck is true")
            }

        }

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }


    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
        mButtonClicked = true
    }

}