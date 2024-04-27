package com.scorepsc.ui.help

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.scorepsc.R
import com.scorepsc.databinding.FragmentHelpBinding
import com.scorepsc.ui.base.BaseFragment
import com.scorepsc.util.Util
import com.scorepsc.util.Util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HelpFragment : BaseFragment(R.layout.fragment_help) {

    private val helpViewModel: HelpViewModel by viewModels()
    private var binding: FragmentHelpBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHelpBinding.bind(view)

        binding?.submit?.setOnClickListener {

            if (binding?.message?.text.isNullOrEmpty()) {
                binding?.message?.error = getString(R.string.error_message)
            } else {
                binding?.statusIndicatorLayout?.root?.isVisible = true
                binding?.statusIndicatorLayout?.statusText?.text =
                    getString(R.string.submitting_query)
                it.hideKeyboard()
                helpViewModel.submitFeedback(binding?.message?.text.toString())
            }
        }
        helpViewModel.whatsAppNumber()
        binding?.whatsapp?.setOnClickListener {
            if (helpViewModel.whatsAppNumberResponse.value == null) {
                helpViewModel.whatsAppNumber()
                binding?.whatsapp?.isEnabled = false
                helpViewModel.whatsAppNumberResponse.observe(viewLifecycleOwner, {
                    Util.navigateToWhatsApp(
                        requireContext(),
                        helpViewModel.whatsAppNumberResponse.value?.data?.data?.whatsapp
                    )
                    binding?.whatsapp?.isEnabled = true
                })
                helpViewModel.whatsAppNumberErrorMessage.observe(viewLifecycleOwner, {
                    showAlert(
                        message = it,
                        positiveButtonClick = { dialog: DialogInterface, i: Int ->
                            dialog.dismiss()
                        })
                    binding?.whatsapp?.isEnabled = true
                })
            } else {
                Util.navigateToWhatsApp(
                    requireContext(),
                    helpViewModel.whatsAppNumberResponse.value?.data?.data?.whatsapp
                )
            }

        }

        helpViewModel.feedbackResponse.observe(viewLifecycleOwner, {
            binding?.statusIndicatorLayout?.root?.isVisible = false
            binding?.message?.text = null
            showAlert(
                message = it.data?.message,
                positiveButtonClick = { dialog: DialogInterface, i: Int ->
                    dialog.dismiss()
                })
        })

        helpViewModel.feedbackErrorMessage.observe(viewLifecycleOwner, {
            binding?.statusIndicatorLayout?.root?.isVisible = false
            showAlert(message = it, positiveButtonClick = { dialog: DialogInterface, i: Int ->
                dialog.dismiss()
            })
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}