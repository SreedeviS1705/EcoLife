package com.scorepsc.ui.home.currentAffairs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.scorepsc.R
import com.scorepsc.databinding.FragmentCurrentAffairsBinding
import com.scorepsc.service.respose.homeBanner.currentAfairs.CurrentAfairsResponse
import com.scorepsc.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CurrentAffairsFragment : BaseFragment(R.layout.fragment_current_affairs), ICurrentAffairs {

    companion object {
        const val TAG = "CurrentAffairsFragment"
    }

    private val viewModel: CurrentAffairsViewModel by viewModels()
    private var binding: FragmentCurrentAffairsBinding? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCurrentAffairsBinding.bind(view)
        showProgressBar(View.VISIBLE)
        viewModel.getCurrentAffairs()

        val mAdapter = CurrentAffairsAdapter(this)
        val layoutManager = GridLayoutManager(activity, 2)
        binding?.recyclerView?.layoutManager = layoutManager
        binding?.recyclerView?.adapter = mAdapter


        viewModel.currentAfairsResponse.observe(viewLifecycleOwner) {
            it.data?.data?.let { it1 -> mAdapter.updateList(it1) }
            showProgressBar(View.GONE)
            if(it.data?.data?.isEmpty() == true) {
                binding?.noItemsFound?.visibility = View.VISIBLE
                binding?.noItemsFound?.text =  "No items found"
            } else {
                binding?.noItemsFound?.visibility = View.GONE
            }
        }

        viewModel.currentAfairsErrorMessage.observe(viewLifecycleOwner) {
            binding?.noItemsFound?.visibility = View.VISIBLE
            binding?.noItemsFound?.text =  "Error loading the data."
            showProgressBar(View.GONE)
        }

    }

    private fun showProgressBar(status:Int) {
        binding?.progressbarId?.visibility = status
    }

    override fun clickEvent(item: CurrentAfairsResponse.Data?) {
        val bundle = Bundle()
        bundle.putString("groupId",item?.id)
        navigate(R.id.currentAffairsByGroupFragment, bundle)
    }
}