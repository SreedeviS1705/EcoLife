package com.scorepsc.ui.home.currentAffairs.byGroup

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.scorepsc.R
import com.scorepsc.databinding.FragmentCurrentAffairsByGroupBinding
import com.scorepsc.service.respose.homeBanner.currentAfairs.byGroup.Post
import com.scorepsc.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CurrentAffairsByGroupFragment : BaseFragment(R.layout.fragment_current_affairs_by_group), ICurrentAffairsByGroup {

    companion object {
        const val TAG = "CurrentAffairsByGroupFragment"
    }

    private val viewModel: CurrentAffairsByGroupViewModel by viewModels()
    private var binding: FragmentCurrentAffairsByGroupBinding? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCurrentAffairsByGroupBinding.bind(view)
        showProgressBar(View.VISIBLE)

        val groupId = arguments?.getString("groupId")
        groupId?.let {
            viewModel.getCurrentAffairsByGroup(groupId.toInt())
        }

        val mAdapter = CurrentAffairsByGroupAdapter(this)
        binding?.recyclerView?.adapter = mAdapter


        viewModel.currentAfairsByGroupResponse.observe(viewLifecycleOwner) {
            showProgressBar(View.GONE)
            //binding?.title?.text = it.data?.data?.group?.name
            if(it.data?.data?.posts?.isEmpty() == true) {
                binding?.noItemsFound?.visibility = View.VISIBLE
                binding?.noItemsFound?.text =  "No items found"
            } else {
                binding?.noItemsFound?.visibility = View.GONE
                it.data?.data?.posts?.let { it1 -> mAdapter.updateList(it1) }
            }
        }

        viewModel.currentAfairsByGroupErrorMessage.observe(viewLifecycleOwner) {
            binding?.noItemsFound?.visibility = View.VISIBLE
            binding?.noItemsFound?.text =  "Error loading the data."
            showProgressBar(View.GONE)
        }

    }

    private fun showProgressBar(status:Int) {
        binding?.progressbarId?.visibility = status
    }

    override fun clickEvent(item: Post?) {
       val bundle = Bundle()
        bundle.putString("post_id", item?.id)
        navigate(R.id.currentAffairsDetailsFragment, bundle)
    }
}