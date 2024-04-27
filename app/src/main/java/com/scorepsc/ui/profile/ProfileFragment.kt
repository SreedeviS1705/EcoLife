package com.scorepsc.ui.profile

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputFilter
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import com.scorepsc.R
import com.scorepsc.databinding.FragmentProfileBinding
import com.scorepsc.ui.base.BaseFragment
import com.scorepsc.util.Util.hideKeyboard
import com.scorepsc.util.Util.isEmailValid
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment(R.layout.fragment_profile) {


    private val profileViewModel: ProfileViewModel by activityViewModels()
    private var binding: FragmentProfileBinding? = null
    private lateinit var imageLoader: ImageLoader

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        imageLoader = ImageLoader.Builder(requireContext())
            .componentRegistry {
                add(SvgDecoder(requireContext()))
            }.build()


        binding?.statusIndicatorLayout?.root?.isVisible = true
        binding?.statusIndicatorLayout?.statusText?.text = getString(R.string.fetching_profile)

        profileViewModel.response.observe(viewLifecycleOwner, { response ->
            binding?.statusIndicatorLayout?.root?.isVisible = false

            val data = response.second?.data?.data ?: return@observe

            binding?.name?.setText(data.name)
            binding?.phone?.setText(data.mobile)
            binding?.state?.setText(data.state)
            binding?.district?.setText(data.district)
            binding?.parentName?.setText(data.guardian)
            binding?.parentPhone?.setText(data.guardianMob)
            binding?.email?.setText(data.email)
            binding?.academicLevel?.text =
                getString(R.string.selected_academic_level) + " " + data.academicLevel

            val pc = response.first?.data?.data?.find {
                it.phonecode == data.phoneCode
            }
            binding?.ccImage?.load(pc?.flag, imageLoader)
            binding?.ccText?.text = pc?.phonecode

            val ppc = response.first?.data?.data?.find {
                it.phonecode == data.parentPhoneCode
            } ?: return@observe
            profileViewModel.updateProfileRequest.parentPhoneCode = ppc.phonecode
            binding?.parentCCImage?.load(ppc.flag, imageLoader)
            binding?.parentCCText?.text = ppc.phonecode
            binding?.parentPhone?.filters = arrayOf(ppc.maxPhoneLength.toIntOrNull()?.let {
                InputFilter.LengthFilter(
                    it
                )
            })

            binding?.parentCCImage?.setOnClickListener {
                showCountryPicker(imageLoader, response.first?.data?.data) { it ->
                    profileViewModel.updateProfileRequest.parentPhoneCode = it.phonecode
                    binding?.parentCCImage?.load(it.flag, imageLoader)
                    binding?.parentCCText?.text = it.phonecode
                    binding?.parentPhone?.filters = arrayOf(it.maxPhoneLength.toIntOrNull()?.let { maxLength ->
                        InputFilter.LengthFilter(
                            maxLength
                        )
                    })
                }
            }
        })



        profileViewModel.profileErrorMessage.observe(viewLifecycleOwner, {
            binding?.statusIndicatorLayout?.statusText?.text = it
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = true
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = false
            binding?.statusIndicatorLayout?.statusText?.isVisible = true
        })

        profileViewModel.countryErrorMessage.observe(viewLifecycleOwner, {
            binding?.statusIndicatorLayout?.statusText?.text = it
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = true
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = false
            binding?.statusIndicatorLayout?.statusText?.isVisible = true
        })

        profileViewModel.getCountry()
        profileViewModel.getProfile()

        binding?.statusIndicatorLayout?.statusRetry?.setOnClickListener {
            binding?.statusIndicatorLayout?.statusRetry?.isVisible = false
            binding?.statusIndicatorLayout?.statusIndicator?.isVisible = true
            binding?.statusIndicatorLayout?.statusText?.isVisible = false
            profileViewModel.getProfile()
            profileViewModel.getCountry()
        }

        profileViewModel.profileUpdateResponse.observe(viewLifecycleOwner, {
            binding?.statusIndicatorLayout?.root?.isVisible = false
            showAlert(
                message = it.data?.message,
                positiveButtonClick = { dialog: DialogInterface, i: Int ->
                    dialog.dismiss()
                })
        })
        profileViewModel.profileUpdateErrorMessage.observe(viewLifecycleOwner, {
            binding?.statusIndicatorLayout?.root?.isVisible = false
            showAlert(message = it, positiveButtonClick = { dialog: DialogInterface, i: Int ->
                dialog.dismiss()
            })
        })

        binding?.state?.setOnClickListener {
            profileViewModel.stateDistrict.observe(viewLifecycleOwner, {
                val stateArray = it.states.map { it.state }
                showStatePicker(stateArray)
            })
            it.hideKeyboard()
        }

        binding?.district?.setOnClickListener {
            profileViewModel.stateDistrict.observe(viewLifecycleOwner, {
                val state = it.states.find {
                    it.state == binding?.state?.text.toString()
                }
                val districts = state?.districts
                showDistrictPicker(districts)
            })
            it.hideKeyboard()
        }

        binding?.update?.setOnClickListener {
            it.hideKeyboard()
            resetInputError()
            when {
                binding?.name?.text.isNullOrEmpty() -> {
                    binding?.nameError?.error = getString(R.string.error_enter_name)
                }
                binding?.state?.text.isNullOrEmpty() -> {
                    binding?.stateError?.error = getString(R.string.error_select_state)
                }
                binding?.district?.text.isNullOrEmpty() -> {
                    binding?.districtError?.error = getString(R.string.error_select_state)
                }
                binding?.parentName?.text.isNullOrEmpty() -> {
                    binding?.parentNameError?.error = getString(R.string.error_enter_parent_name)
                }
                binding?.parentPhone?.text?.length != 10 -> {
                    binding?.parentPhone?.error =
                        getString(R.string.error_invalid_mobile_number)
                }
                binding?.email?.text.isNullOrEmpty() -> {
                    binding?.emailError?.error = getString(R.string.error_email_id)
                }
                !binding?.email?.text.toString().isEmailValid() -> {
                    binding?.emailError?.error = getString(R.string.error_invalid_email)
                }
                else -> {
                    doUpdate()
                }
            }

        }

    }

    private fun doUpdate() {

        profileViewModel.updateProfileRequest.name = binding?.name?.text.toString()
        profileViewModel.updateProfileRequest.state = binding?.state?.text.toString()
        profileViewModel.updateProfileRequest.district = binding?.district?.text.toString()
        profileViewModel.updateProfileRequest.guardian = binding?.parentName?.text.toString()
        profileViewModel.updateProfileRequest.guardianMob = binding?.parentPhone?.text.toString()
        profileViewModel.updateProfileRequest.email = binding?.email?.text.toString()
        profileViewModel.updateProfile()

        binding?.statusIndicatorLayout?.root?.isVisible = true
        binding?.statusIndicatorLayout?.statusText?.text = getString(R.string.updating_profile)
        binding?.statusIndicatorLayout?.root?.hideKeyboard()

    }

    private fun resetInputError() {
        binding?.nameError?.error = null
        binding?.stateError?.error = null
        binding?.districtError?.error = null
        binding?.parentNameError?.error = null
        binding?.parentPhone?.error = null
        binding?.emailError?.error = null
    }


    private fun showStatePicker(stateArray: List<String>) {
        AlertDialog.Builder(requireContext())
            .setSingleChoiceItems(stateArray.toTypedArray(), 0) { dialog, position ->
                binding?.state?.setText(stateArray!![position])
                binding?.district?.isEnabled = true
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