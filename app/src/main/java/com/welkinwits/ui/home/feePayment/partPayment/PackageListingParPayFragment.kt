package com.welkinwits.ui.home.feePayment.partPayment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.welkinwits.R
import com.welkinwits.databinding.FragmentPackageListingPartPaymentBinding
import com.welkinwits.service.respose.homeBanner.myPartPayment.MySubscriptionPackageListResponse
import com.welkinwits.ui.base.BaseFragment
import com.welkinwits.ui.home.feePayment.feePaymentModeOfStudy.FeePaymentModeOfStudyFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PackageListingParPayFragment : BaseFragment(R.layout.fragment_package_listing_part_payment),IMySubscriptionPackageListing {

    companion object {
        const val TAG = "PackageListingParPayFragment"
    }
    private val viewModel: MySubscriptionPackageListViewModel by viewModels()
    private var binding: FragmentPackageListingPartPaymentBinding? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPackageListingPartPaymentBinding.bind(view)

        viewModel.getMySubscriptionPackageList()
        showProgressBar(View.VISIBLE)
        val mAdapter = MySubscriptionPackageListingAdapter(this)
         binding?.recyclerViewSubscription?.adapter = mAdapter

        viewModel.mySubscriptionPackageListResponse.observe(viewLifecycleOwner) {
            Log.d(FeePaymentModeOfStudyFragment.TAG, "mySubscriptionPackageListResponse: size" + it.data?.data?.size)
            mAdapter?.updateList(it.data?.data)
            showProgressBar(View.GONE)
        }
        viewModel.mySubscriptionPackageListErrorMessage.observe(viewLifecycleOwner){
            Log.d(FeePaymentModeOfStudyFragment.TAG, "mySubscriptionPackageListErrorMessage: ")
            showProgressBar(View.GONE)
        }
    }

    private fun showProgressBar(visible: Int) {
        if(visible == View.GONE) {
            binding?.dataContainer?.visibility = View.VISIBLE
            binding?.progressBarContainer?.visibility = View.GONE
        } else {
            binding?.dataContainer?.visibility = View.VISIBLE
            binding?.progressBarContainer?.visibility = View.VISIBLE
        }
    }

    override fun itemClickEvent(item: MySubscriptionPackageListResponse.Datum?) {
        val bundle = Bundle()
        bundle.putString("packageId", item?.packageId.toString())
        bundle.putString("duration",item?.tenure)
        bundle.putString("enrollmentId", item?.enrollementId)
        bundle.putString("type", "byPackage")
        navigate(R.id.part_payment_list, bundle)
    }
}