package com.welkinwits.ui.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.welkinwits.MainActivity
import com.welkinwits.R
import com.welkinwits.databinding.GetStartFragmentBinding
import com.welkinwits.ui.base.BaseFragment
import com.welkinwits.util.DataStoreManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class GetStartFragment : BaseFragment(R.layout.get_start_fragment) {
    companion object {
        const val TAG = "FFFFFFFFFFFFFF"
    }
    private var studId: String?= null
    private var token: String?= null


    private var binding: GetStartFragmentBinding? = null
    @Inject
    lateinit var dataStoreManager: DataStoreManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
        arguments?.let {

            studId = requireArguments().getString("studId")
            token = requireArguments().getString("token")
        }
        binding = GetStartFragmentBinding.bind(view)
        binding?.toolbar?.setNavigationOnClickListener { findNavController().popBackStack() }
        binding?.startBtn?.setOnClickListener {
            Log.d(TAG, "startBtn: ")
            GlobalScope.launch {
                studId?.let { it1 -> dataStoreManager?.setStudId(it1) }
                token?.let { it1 -> dataStoreManager?.setToken(it1) }
            }

            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView: ")
        binding = null
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
    }

}