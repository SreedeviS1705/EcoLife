package com.witsclassdevelopment.ui.home.flashCard

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.witsclassdevelopment.R
import com.witsclassdevelopment.databinding.FragmentFlashCardGroupBinding
import com.witsclassdevelopment.service.respose.homeBanner.flashCard.FlashCardGroupResponse
import com.witsclassdevelopment.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlashCardGroupFragment : BaseFragment(R.layout.fragment_flash_card_group), IFlashCardGroup {

    companion object {
        const val TAG = "FlashCardGroupFragment"
    }

    private val viewModel: FlashCardGroupViewModel by viewModels()
    private var binding: FragmentFlashCardGroupBinding? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFlashCardGroupBinding.bind(view)
        binding?.progressBarId?.visibility = View.VISIBLE
        viewModel.getFlashCardGroupResponse()

      val mAdapter = FlashCardGroupAdapter(this)
        binding?.recyclerView?.layoutManager = GridLayoutManager(activity,2, RecyclerView.VERTICAL,false)

        binding?.recyclerView?.adapter = mAdapter

        viewModel.flashCardGroupResponse.observe(viewLifecycleOwner) {
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
        Log.d(TAG, "clickEvent: ")
        val bundle = Bundle()
        bundle.putString("id",item.id)
        bundle.putString("name",item.name)
        navigate(R.id.fragmentFlashCardItems, bundle)
    }
}