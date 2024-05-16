package com.welkinwits.ui.signup

import android.os.Bundle
import android.text.InputFilter
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import com.welkinwits.R
import com.welkinwits.databinding.FragmentGeneralDetailsBinding
import com.welkinwits.ui.base.BaseFragment
import com.welkinwits.util.Util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GeneralDetailFragment : BaseFragment(R.layout.fragment_general_details) {


    private val viewModel: SignUpViewModel by activityViewModels()
    private var binding: FragmentGeneralDetailsBinding? = null
    private lateinit var imageLoader: ImageLoader

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageLoader = ImageLoader.Builder(requireContext())
            .componentRegistry {
                add(SvgDecoder(requireContext()))
            }.build()

        binding = FragmentGeneralDetailsBinding.bind(view)
        binding?.toolbar?.setNavigationOnClickListener { findNavController().popBackStack() }

        binding?.statusIndicatorLayout?.root?.isVisible = true
        binding?.statusIndicatorLayout?.statusText?.text = getString(R.string.please_wait)

        viewModel.countryResponse.observe(viewLifecycleOwner) { res ->
            binding?.statusIndicatorLayout?.root?.isVisible = false
            if (res.data?.data?.isNullOrEmpty() == false) {
                val first = res.data.data.first()
                viewModel.signUpRequest.phoneCode = first.phonecode
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
                    viewModel.signUpRequest.phoneCode = it.phonecode
                    binding?.ccImage?.load(it.flag, imageLoader)
                    binding?.ccText?.text = it.phonecode
                    binding?.phone?.filters = arrayOf(it.maxPhoneLength.toIntOrNull()?.let {
                        InputFilter.LengthFilter(
                            it
                        )
                    })
                }
            }
        }

        viewModel.countryErrorMessage.observe(viewLifecycleOwner) {
            binding?.statusIndicatorLayout?.statusText?.text = it
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = true
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = false
            binding?.statusIndicatorLayout?.statusText?.isVisible = true
        }

        binding?.statusIndicatorLayout?.statusRetry?.setOnClickListener {
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = false
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = true
            binding?.statusIndicatorLayout?.statusText?.isVisible = false
            viewModel.getCountry()
        }

        viewModel.getCountry()

        binding?.state?.setOnClickListener {
            viewModel.stateDistrict.observe(viewLifecycleOwner) {
                val stateArray = it.states.map { it.state }
                showStatePicker(stateArray)
            }
            it.hideKeyboard()
        }

        binding?.district?.setOnClickListener {
            viewModel.stateDistrict.observe(viewLifecycleOwner) {
                val state = it.states.find {
                    it.state == binding?.state?.text.toString()
                }
                val districts = state?.districts
                showDistrictPicker(districts)
            }
            it.hideKeyboard()
        }

/*        binding?.idProof?.setOnClickListener {
            it.hideKeyboard()
            with(ImagePicker.with(this)) {
                compress(256)
                maxResultSize(320, 320)
                crop()
                galleryOnly()
                start { resultCode, data ->
                    when (resultCode) {
                        Activity.RESULT_OK -> {
                            //Image Uri will not be null for RESULT_OK
                            val file = ImagePicker.getFile(data)
                            binding?.idProof?.setText(file?.absolutePath.toString())
                            viewModel.signUpRequest.idProof = file?.toBase64()

                        }
                        ImagePicker.RESULT_ERROR -> {
                            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT)
                                .show()
                        }
                        else -> {
                            Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

        }

        binding?.uploadPhoto?.setOnClickListener {
            with(ImagePicker.with(this)) {
                compress(256)
                maxResultSize(320, 320)
                cropSquare()
                galleryOnly()
                start { resultCode, data ->
                    when (resultCode) {
                        Activity.RESULT_OK -> {
                            //Image Uri will not be null for RESULT_OK
                            val file = ImagePicker.getFile(data)
                            binding?.uploadPhoto?.setText(file?.absolutePath.toString())
                            viewModel.signUpRequest.profilePic = file?.toBase64()

                        }
                        ImagePicker.RESULT_ERROR -> {
                            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT)
                                .show()
                        }
                        else -> {
                            Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }*/

        binding?.next?.setOnClickListener {
            it.hideKeyboard()
            resetInputError()

            when {
                binding?.name?.text.isNullOrEmpty() -> {
                    binding?.nameError?.error = getString(R.string.error_enter_name)
                }
                binding?.phone?.text.isNullOrEmpty() -> {
                    binding?.phone?.error = getString(R.string.error_invalid_mobile_number)
                }
                binding?.state?.text.isNullOrEmpty() -> {
                    binding?.stateError?.error = getString(R.string.error_select_state)
                }
                binding?.district?.text.isNullOrEmpty() -> {
                    binding?.districtError?.error = getString(R.string.error_select_district)
                }
                /*binding?.idProof?.text.isNullOrEmpty() -> {
                    binding?.idProofError?.error = getString(R.string.error_select_id)
                }
                binding?.uploadPhoto?.text.isNullOrEmpty() -> {
                    binding?.uploadPhotoError?.error = getString(R.string.error_select_avatar)
                }*/
                else -> goToNext()
            }
        }
    }

    private fun resetInputError() {
        binding?.nameError?.error = null
        binding?.phone?.error = null
        binding?.stateError?.error = null
        binding?.districtError?.error = null
        //binding?.idProofError?.error = null
    }

    private fun goToNext() {
        viewModel.signUpRequest.name = binding?.name?.text.toString()
        viewModel.signUpRequest.mobile = binding?.phone?.text.toString()
        viewModel.signUpRequest.state = binding?.state?.text.toString()
        viewModel.signUpRequest.district = binding?.district?.text.toString()
        navigate(R.id.parentDetailsFragment)
    }


    private fun showStatePicker(stateArray: List<String>) {
        AlertDialog.Builder(requireContext())
            .setSingleChoiceItems(stateArray.toTypedArray(), 0) { dialog, position ->
                binding?.state?.setText(stateArray[position])
                binding?.district?.text = null
                binding?.districtError?.isEnabled = true
                dialog.dismiss()
            }.show()
    }

    private fun showDistrictPicker(districts: List<String>?) {
        AlertDialog.Builder(requireContext())
            .setSingleChoiceItems(districts?.toTypedArray(), 0) { dialog, position ->
                binding?.district?.setText(districts!![position])
                dialog.dismiss()
            }.show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}