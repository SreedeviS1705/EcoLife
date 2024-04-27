package com.scorepsc.ui.home.flashCard.flashCardItems

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.scorepsc.R
import com.scorepsc.databinding.FragmentFlashCardItemsBinding
import com.scorepsc.service.respose.homeBanner.flashCard.FlashCardGroupResponse
import com.scorepsc.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlashCardItemsFragment : BaseFragment(R.layout.fragment_flash_card_items), IFlashCardItems {

    companion object {
        const val TAG = "FlashCardItemsFragment"
    }

    private val viewModel: FlashCardItemsViewModel by viewModels()
    private var binding: FragmentFlashCardItemsBinding? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFlashCardItemsBinding.bind(view)
        val id = arguments?.getString("id")
        val name = arguments?.getString("name")
        binding?.progressBarId?.visibility = View.VISIBLE
        binding?.subject?.text = name
        id?.let { viewModel.getFlashCardItemsResponse(it) }
      val mAdapter = FlashCardItemsAdapter(this)

        binding?.recyclerView?.adapter = mAdapter

        viewModel.flashCardItemsResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "enteranceExamAnswerKeyResponse: size" + it.data?.data?.size)
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                binding?.progressBarId?.visibility = View.GONE
                if (it?.data?.data?.isEmpty() == true) {
                    binding?.noDataAvailable?.visibility = View.VISIBLE
                } else {
                    binding?.noDataAvailable?.visibility = View.GONE
                    mAdapter.updateList(it?.data?.data)
                }
            }, 500)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            binding?.progressBarId?.visibility = View.GONE
            binding?.noDataAvailable?.visibility = View.VISIBLE
            binding?.noDataAvailable?.text = "Error Occurred"
        }

    }

    override fun onPause() {
        super.onPause()
        binding?.progressBarId?.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        binding?.progressBarId?.visibility = View.GONE
    }

    override fun clickEvent(item: FlashCardGroupResponse.Datum) {
/*        Log.d(TAG, "clickEvent: ")
        val bundle = Bundle()
        bundle.putString("id",item.id)
        navigate(R.id.fragmentQAAnswerKey, bundle)*/
    }
}