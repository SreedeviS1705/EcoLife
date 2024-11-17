package com.welkinwits.ui.verifyotp

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.welkinwits.R
import com.welkinwits.databinding.FragmentVerifyOtpBinding
import com.welkinwits.ui.base.BaseFragment
import com.welkinwits.util.Util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class VerifyOTPFragment : BaseFragment(R.layout.fragment_verify_otp) {
    companion object{
        const val TAG = "VerifyOTPFragment"
    }


    private val viewModel: VerifyOTViewModel by viewModels()
    private var binding: FragmentVerifyOtpBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentVerifyOtpBinding.bind(view)

        val otpRef = arguments?.getString("otpRef")

        setResendOTPCounter()

        binding?.otpView?.setOtpCompletionListener {
            binding?.submit?.isEnabled = true
        }

        binding?.submit?.setOnClickListener {
            val otp = binding?.otpView?.text.toString()
            viewModel.verifyOTP(otpRef?.toInt(), otp)
            binding?.statusIndicatorLayout?.root?.isVisible = true
            binding?.statusIndicatorLayout?.statusText?.text = getString(R.string.signing_in)
            binding?.statusIndicatorLayout?.root?.hideKeyboard()
        }

        viewModel.verifyOTPResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "verifyOTPResponse: ")
            var bundle = Bundle()
            bundle.putString("studId",it.data?.data?.studId)
            bundle.putString("token",it.data?.token)
            Log.d(TAG, "onViewCreated: "+it.data?.data?.profile?.name)
            bundle.putString("name",it.data?.data?.profile?.name)
            navigate(R.id.getStartedFragment, bundle)

            /*it.data?.data?.studId?.let { it1 ->
                it.data?.token?.let { it2 ->
                    VerifyOTPFragmentDirections.actionOtpFragmentToNavigationGetStartScreen(
                        it1, it2
                    )
                }
            }*/
        }
        viewModel.verifyOTPErrorMessage.observe(viewLifecycleOwner) {
            binding?.statusIndicatorLayout?.root?.isVisible = false
            showAlert(message = it, positiveButtonClick = { dialog: DialogInterface, i: Int ->
                dialog.dismiss()
            })
        }
        binding?.resend?.setOnClickListener {
            binding?.resend?.isVisible = false
            viewModel.resendOTP(otpRef?.toInt())
        }



    }

    private fun setResendOTPCounter() {
        object : CountDownTimer(30000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                binding?.resend?.text = "Resend OTP in: ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                binding?.resend?.isEnabled = true
                binding?.resend?.setTextColor(Color.BLUE)
                binding?.resend?.text = getString(R.string.resend_otp)
                binding?.resend?.paintFlags =
                    binding?.resend?.paintFlags!! or Paint.UNDERLINE_TEXT_FLAG
            }
        }.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}