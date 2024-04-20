package com.witsclassdevelopment.ui.signin

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputFilter
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import com.witsclassdevelopment.R
import com.witsclassdevelopment.databinding.FragmentSignInBinding
import com.witsclassdevelopment.ui.base.BaseFragment
import com.witsclassdevelopment.util.Util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

    private val viewModel: SignInViewModel by viewModels()
    private var binding: FragmentSignInBinding? = null
    private lateinit var imageLoader: ImageLoader
    private lateinit var phoneCode: String
    private var loginCheck = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageLoader = ImageLoader.Builder(requireContext())
            .componentRegistry {
                add(SvgDecoder(requireContext()))
            }.build()

        binding = FragmentSignInBinding.bind(view)

        //binding?.toolbar?.setNavigationOnClickListener { findNavController().popBackStack() }

        binding?.next?.setOnClickListener {
            it.hideKeyboard()
            val phone = binding?.phone?.text
            if (phone.isNullOrEmpty()) {
                binding?.phone?.error = getString(R.string.error_invalid_mobile_number)
            } else {
                doSignIn()
            }
        }
        binding?.createAccount?.setOnClickListener {
            navigate(R.id.generalDetailFragment)
        }

        viewModel.signInResponse.observe(viewLifecycleOwner) {
            /*val data = Bundle().apply { putInt("otpRef", it.data?.data?.otpRef ?: -1) }
            navigate(R.id.verifyOTPFragment, data)*/

            val action = SignInFragmentDirections.actionSiginFragmentToNavigatioOtpScreen(it.data?.data?.otpRef.toString())
            action?.let { it1 -> findNavController().navigate(it1) }


            binding?.statusIndicatorLayout?.statusIndicator?.visibility = View.GONE
            binding?.statusIndicatorLayout?.statusText?.visibility = View.GONE
            binding?.statusIndicatorLayout?.statusRetry?.visibility = View.GONE
        }
        viewModel.signInErrorMessage.observe(viewLifecycleOwner) {
            binding?.statusIndicatorLayout?.statusIndicator?.visibility = View.GONE
            binding?.statusIndicatorLayout?.statusText?.visibility = View.GONE
            binding?.statusIndicatorLayout?.statusRetry?.visibility = View.GONE
            if (loginCheck) {
                binding?.statusIndicatorLayout?.root?.isVisible = false
                showAlert(message = it, positiveButtonClick = { dialog: DialogInterface, i: Int ->
                    dialog.dismiss()
                })
            } else {
                loginCheck = false
            }
        }


        binding?.statusIndicatorLayout?.root?.isVisible = true
        binding?.statusIndicatorLayout?.statusText?.text = getString(R.string.please_wait)

        viewModel.countryResponse.observe(viewLifecycleOwner, { res ->
            binding?.statusIndicatorLayout?.root?.isVisible = false
            if (res.data?.data?.isNullOrEmpty() == false) {
                val first = res.data.data.first()
                phoneCode = first.phonecode
                binding?.ccImage?.load(first.flag, imageLoader)
                binding?.ccText?.text = first.phonecode
                binding?.phone?.filters = arrayOf(first.maxPhoneLength.toIntOrNull()?.let {
                    InputFilter.LengthFilter(
                        it
                    )
                })
            }

            binding?.ccImage?.setOnClickListener {
                showCountryPicker(imageLoader, res.data?.data) {
                    phoneCode = it.phonecode
                    binding?.ccImage?.load(it.flag, imageLoader)
                    binding?.ccText?.text = it.phonecode
                    binding?.phone?.filters = arrayOf(it.maxPhoneLength.toIntOrNull()?.let {
                        InputFilter.LengthFilter(
                            it
                        )
                    })
                }
            }
        })


        viewModel.countryErrorMessage.observe(viewLifecycleOwner, {
            binding?.statusIndicatorLayout?.statusText?.text = it
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = true
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = false
            binding?.statusIndicatorLayout?.statusText?.isVisible = true
        })

        binding?.statusIndicatorLayout?.statusRetry?.setOnClickListener {
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = false
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = true
            binding?.statusIndicatorLayout?.statusText?.isVisible = false
            viewModel.getCountry()
        }

        viewModel.getCountry()
    }

    private fun doSignIn() {
        loginCheck = true
        viewModel.signIn(binding?.phone?.text.toString(), phoneCode)
        binding?.statusIndicatorLayout?.statusIndicator?.visibility = View.VISIBLE
        binding?.statusIndicatorLayout?.statusText?.visibility = View.VISIBLE
        binding?.statusIndicatorLayout?.statusRetry?.visibility = View.VISIBLE
        binding?.statusIndicatorLayout?.statusText?.text = getString(R.string.signing_in)
        binding?.statusIndicatorLayout?.root?.hideKeyboard()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onStop() {
        super.onStop()
        loginCheck = false
        binding?.statusIndicatorLayout?.statusIndicator?.visibility = View.GONE
        binding?.statusIndicatorLayout?.statusText?.visibility = View.GONE
        binding?.statusIndicatorLayout?.statusRetry?.visibility = View.GONE
    }


}