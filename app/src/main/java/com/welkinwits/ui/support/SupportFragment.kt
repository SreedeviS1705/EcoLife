package com.welkinwits.ui.support

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.welkinwits.R
import com.welkinwits.databinding.FragmentSupportBinding
import com.welkinwits.ui.base.BaseFragment
import com.welkinwits.ui.help.HelpViewModel
import com.welkinwits.util.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SupportFragment : BaseFragment(R.layout.fragment_support) {

    private val helpViewModel: HelpViewModel by viewModels()
    private var binding: FragmentSupportBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSupportBinding.bind(view)

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}