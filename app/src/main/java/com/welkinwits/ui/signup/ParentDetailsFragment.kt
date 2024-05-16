package com.welkinwits.ui.signup

import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.ImageLoader
import coil.decode.SvgDecoder
import com.welkinwits.R
import com.welkinwits.databinding.FragmentParentDetailsBinding
import com.welkinwits.ui.base.BaseFragment
import com.welkinwits.util.Util.hideKeyboard
import com.welkinwits.util.Util.isEmailValid
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ParentDetailsFragment : BaseFragment(R.layout.fragment_parent_details) {
    companion object {
        const val TAG = "ParentDetailsFragment"
    }

    private val viewModel: SignUpViewModel by activityViewModels()
    private var binding: FragmentParentDetailsBinding? = null
    private lateinit var imageLoader: ImageLoader
    private var referalCode:String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageLoader = ImageLoader.Builder(requireContext())
            .componentRegistry {
                add(SvgDecoder(requireContext()))
            }.build()

        binding = FragmentParentDetailsBinding.bind(view)
        binding?.toolbar?.setNavigationOnClickListener { findNavController().popBackStack() }

        binding?.next?.setOnClickListener {
            it.hideKeyboard()
            resetInputError()
            when {
                binding?.name?.text.isNullOrEmpty() -> {
                    binding?.nameError?.error = getString(R.string.error_enter_parent_name)
                }
                binding?.email?.text.isNullOrEmpty() -> {
                    binding?.emailError?.error = getString(R.string.error_email_id)
                }
                !binding?.email?.text.toString().isEmailValid() -> {
                    binding?.emailError?.error = getString(R.string.error_invalid_email)
                }
                binding?.selectLevel?.text.isNullOrEmpty() -> {
                    binding?.academicError?.error = getString(R.string.error_academic_level)
                }
                else -> {
                    doSignUp()
                }
            }
        }

        viewModel.countryResponse.observe(viewLifecycleOwner) { res ->
            if (res.data?.data?.isNullOrEmpty() == false) {
                val first = res.data.data.first()
                viewModel.signUpRequest.parentPhoneCode = first.phonecode
            }
        }

        viewModel.signUpResponse.observe(viewLifecycleOwner) {
            val data = Bundle().apply { putString("otpRef", it.data?.data?.otpRef.toString()) }
            navigate(R.id.verifyOTPFragment, data)
        }

        viewModel.signUpErrorMessage.observe(viewLifecycleOwner) {
            binding?.statusIndicatorLayout?.root?.isVisible = false
            showAlert(message = it, positiveButtonClick = { dialog: DialogInterface, i: Int ->
                dialog.dismiss()
            })
        }

        viewModel.refferalCodeResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "refferalCodeResponse: ")
            Handler(Looper.getMainLooper()).postDelayed({
                it.data?.data?.referralCode?.let {
                    referalCode = it
                }
                showReferralProgress(false)
                binding?.referalVerifyStatus?.visibility = View.VISIBLE
                if(it.data?.data?.name != null) {
                    binding?.referalVerifyStatus?.text = "Verified Name : "+it.data?.data?.name
                } else {
                    binding?.referalVerifyStatus?.text = "Referral code not found."
                }

            }, 1000)

        }

        viewModel.refferalCodeErrorMessage.observe(viewLifecycleOwner) {
            Log.d(TAG, "refferalCodeErrorMessage: ")
        }

        viewModel.academicLevel()
        binding?.selectLevel?.setOnClickListener {
            if (viewModel.academicLevelResponse.value == null) {
                viewModel.academicLevel()
                binding?.selectLevel?.isEnabled = false
                viewModel.academicLevelResponse.observe(viewLifecycleOwner) {
                    showAcademicPicker(viewModel.academicLevelResponse.value?.data?.data?.map {
                        it.levels
                    }!!)
                    binding?.selectLevel?.isEnabled = true
                }
                viewModel.academicLevelErrorMessage.observe(viewLifecycleOwner) {
                    showAlert(
                        message = it,
                        positiveButtonClick = { dialog: DialogInterface, i: Int ->
                            dialog.dismiss()
                        })
                    binding?.selectLevel?.isEnabled = true
                }
            } else {
                showAcademicPicker(viewModel.academicLevelResponse.value?.data?.data?.map {
                    it.levels
                }!!)
            }
        }

        binding?.referalCodeVerifyBtn?.setOnClickListener {
            Log.d(TAG, "referalCodeVerifyBtn: ")
            if(binding?.refferal?.text.toString().isEmpty()) {
                Toast.makeText(activity, "Please enter Referral Code", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.verrifyReferralCode(binding?.refferal?.text.toString())
                showReferralProgress(true)
            }
        }
    }

    private fun showReferralProgress(check: Boolean) {
        if(check) {
            binding?.referalCodeVerifyBtn?.visibility = View.GONE
            binding?.refferalCodeProgress?.visibility = View.VISIBLE
        } else {
            binding?.referalCodeVerifyBtn?.visibility = View.VISIBLE
            binding?.refferalCodeProgress?.visibility = View.GONE
        }

    }

    private fun doSignUp() {
        viewModel.signUpRequest.guardian = binding?.name?.text.toString()
        viewModel.signUpRequest.email = binding?.email?.text.toString()
        viewModel.signUpRequest.academicLevel = getAcademicId()
        viewModel.signUpRequest.referralCode = referalCode
        viewModel.signUp()

        binding?.statusIndicatorLayout?.root?.isVisible = true
        binding?.statusIndicatorLayout?.statusText?.text = getString(R.string.creating_account)
        binding?.statusIndicatorLayout?.root?.hideKeyboard()
    }

    private fun getAcademicId(): String? =
        viewModel.academicLevelResponse.value?.data?.data?.find {
            it.levels == binding?.selectLevel?.text.toString()
        }?.acId

    private fun showAcademicPicker(academicArray: List<String>) {
        AlertDialog.Builder(requireContext())
            .setSingleChoiceItems(academicArray.toTypedArray(), 0) { dialog, position ->
                binding?.selectLevel?.setText(academicArray[position])
                dialog.dismiss()
            }.show()
    }

    private fun resetInputError() {
        binding?.nameError?.error = null
        binding?.emailError?.error = null
        binding?.academicError?.error = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onStop() {
        super.onStop()
        showReferralProgress(false)
    }
}