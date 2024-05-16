package com.welkinwits.ui.home.allSubscription

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.welkinwits.R
import com.welkinwits.databinding.FragmentAllSubscriptionListBinding
import com.welkinwits.service.respose.homeBanner.subscription.GetAllSubscriptionListResponse
import com.welkinwits.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllSubscriptionListFragment : BaseFragment(R.layout.fragment_all_subscription_list), IAllSubscriptionList {

    companion object {
        const val TAG = "AllSubscriptionListFragment"
    }

    private val viewModel: AllSubscriptionListViewModel by viewModels()
    private var binding: FragmentAllSubscriptionListBinding? = null
    private lateinit var mAdapter: AllSubscriptionListAdapter

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAllSubscriptionListBinding.bind(view)

        viewModel.getAllSubscriptionList()
        showLoader(View.VISIBLE)

        mAdapter = activity?.let { AllSubscriptionListAdapter(it, this) }!!
        binding?.recyclerView?.adapter = mAdapter

        viewModel.getAllSubscriptionListResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "getAllSubscriptionListResponse: size" + it.data?.data?.size)
            val customList: ArrayList<GetAllSubscriptionListResponse.HomeBannerData> = ArrayList()
            var subscriptionStatus:Boolean = false
            Handler(Looper.getMainLooper()).postDelayed({
                showLoader(View.GONE)
                if(it.data?.data?.isEmpty() == true) {
                    binding?.subscriptionNotAvailableText?.visibility = View.VISIBLE
                    binding?.subscriptionNotAvailableText?.text = "subscription Not Available"
                } else {
                    for(listItem in it.data?.data!!) {
                        if(listItem?.subscriptionStatus.equals("1")) {
                            subscriptionStatus = true
                        }
                        customList?.add(listItem)
                    }
                    if(subscriptionStatus) {
                        customList.clear()
                        for(listItem in it.data?.data!!) {
                            if(listItem?.subscriptionStatus.equals("1")) {
                                customList?.add(listItem)
                            }
                        }
                        binding?.subscriptionNotAvailableText?.text = "Other subscription packages wil\n" +
                                "be available after the current\n" +
                                "subscription expiration"
                        binding?.subscriptionNotAvailableText?.visibility = View.VISIBLE
                    } else {
                        binding?.subscriptionNotAvailableText?.visibility = View.GONE
                    }
                }
                it.data?.data?.let { it1 -> mAdapter.updateList(customList) }
            }, 1000)

        }

        viewModel.getAllSubscriptionListErrorMessage.observe(viewLifecycleOwner) {
            Log.d(TAG, "getAllSubscriptionListErrorMessage" )
            showLoader(View.GONE)
            binding?.subscriptionNotAvailableText?.visibility = View.VISIBLE
            binding?.subscriptionNotAvailableText?.text = "Error Loading the data"
        }

    }

    private fun showLoader(status:Int) {
        if(status == View.VISIBLE) {
            binding?.progressBarId?.visibility = View.VISIBLE
        } else {
            binding?.progressBarId?.visibility = View.GONE
        }
    }

    override fun clickEvent(item: GetAllSubscriptionListResponse.HomeBannerData?) {
        Log.d(TAG, "clickEvent: "+item?.id)
        val bundle = Bundle()
        bundle.putString("id", item?.id)
        navigate(R.id.allSubscriptionListDetailsFragment, bundle)
    }
}