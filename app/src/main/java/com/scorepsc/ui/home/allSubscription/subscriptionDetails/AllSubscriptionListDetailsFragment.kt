package com.scorepsc.ui.home.allSubscription.subscriptionDetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.scorepsc.R
import com.scorepsc.databinding.FragmentAllSubsciptionListDetailsBinding
import com.scorepsc.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllSubscriptionListDetailsFragment : BaseFragment(R.layout.fragment_all_subsciption_list_details) {

    companion object {
        const val TAG = "AllSubscriptionListDetailsFragment"
    }

    private val viewModel: AllSubscriptionDetailsListViewModel by viewModels()
    private var binding: FragmentAllSubsciptionListDetailsBinding? = null
    private var orderId:Int? = -1

    private val paymentStatusCheckHandler = Handler(Looper.getMainLooper())
    private var runnable: Runnable?=null
    var paymentStatus = false
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAllSubsciptionListDetailsBinding.bind(view)

        runnable = Runnable {
            Log.d(TAG, "run: ")
            orderId?.let { viewModel?.getOrderStatusSubscriptionList(it) }
            runnable?.let { paymentStatusCheckHandler.postDelayed(it, 4000) }
        }

        val id = arguments?.getString("id")

        if (id != null) {
            viewModel.getAllSubscriptionDetailsList(id)
        }
        showLoader(View.VISIBLE)

        viewModel.getCreateOrderSubscriptionListResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "getAllSubscriptionListResponse: orderId" + it.data?.data?.orderId)
            orderId = it.data?.data?.orderId;
            binding?.subscriptionNotAvailableText?.visibility = View.GONE
            Handler(Looper.getMainLooper()).postDelayed({
                binding?.courseName?.text = it.data?.data?.packageName
                binding?.courseDuration?.text = it.data?.data?.tenure
                binding?.proceedToPay?.visibility = View.VISIBLE
                binding?.courseFee?.text = "Rs "+it.data?.data?.amount+"/- (GST inc)"
                showLoader(View.GONE)
            }, 1000)
        }

        viewModel.getCreateOrderSubscriptionListErrorMessage.observe(viewLifecycleOwner) {
            Log.d(TAG, "getAllSubscriptionListErrorMessage" )
            showLoader(View.GONE)
            binding?.proceedToPay?.visibility = View.GONE
            binding?.subscriptionNotAvailableText?.visibility = View.VISIBLE
        }

        viewModel.getOrderInitSubscriptionListResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "getOrderInitSubscriptionListResponse: ")
            if(!paymentStatus) {
                //Toast.makeText(activity, "bdOrderId: "+it?.data?.data?.bdOrderId, Toast.LENGTH_SHORT).show()
                val oToken: String? = it.data?.data?.authToken
                val bdOrderId: String? = it.data?.data?.bdOrderId
                val url = "https://app.witsclass.com/console/TEXTTUTOR/micros/Api/Payment/payment_screen?bdOrderId=$bdOrderId&authToken=$oToken"
                //val url = "https://www.google.co.in/"
                //val url = "https://app.witsclass.com/console/TEXTTUTOR/micros/Api/Payment/payment_screen?bdOrderId=OAP519XTX2MEFI&authToken=OToken%20E75DBA65AAD66DFDCB209287F157CC6055012BCCE59E31EBDE810799885831BCCD218F602A42D366E2192CD81FCCBCDEB21D5FE4ACD4CB096176E0B38754A0807C4B152E46EE7A73743FAD143918908344AF720CC4527FAD0BE761761B65FCC951E063C3501E471BDFB7298E8D6599A5293DEA4F05487E7299C58832456DBD79FFFD927D6DB3A241454F88D600B4E93EFD724F6E6A856E00CFE0A0.4145535F55415431"
                Log.d(TAG, ""+url)
                binding?.progressBarContainer?.visibility = View.GONE
                binding?.detailsContainer?.visibility = View.GONE
                binding?.paymentContainer?.visibility = View.VISIBLE
                binding?.paymentViewScreen?.getSettings()?.setJavaScriptEnabled(true);
                binding?.paymentViewScreen?.loadUrl(url)
                binding?.paymentViewScreen?.requestFocus()
                orderId?.let { it1 -> viewModel.getOrderStatusSubscriptionList(it1) }
                runnable?.let {
                    paymentStatusCheckHandler.postDelayed(runnable!!, 5000)
                }
            } else {
                Log.d(TAG, "getOrderInitSubscriptionListResponse: else")
            }

        }

        viewModel.getOrderInitSubscriptionListErrorMessage.observe(viewLifecycleOwner) {
            Log.d(TAG, "getOrderInitSubscriptionListErrorMessage " )
            binding?.progressBarContainer?.visibility = View.GONE
            binding?.paymentContainer?.visibility = View.GONE
            binding?.detailsContainer?.visibility = View.VISIBLE
        }

        viewModel.getOrderCreateStatusSubscriptionListResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "getOrderCreateStatusSubscriptionListResponse: ")
            if((it.data?.data?.status == "completed") && !paymentStatus) {
                Log.d(TAG, "getOrderCreateStatusSubscriptionListResponse: completed")
                paymentStatusCheckHandler.removeCallbacksAndMessages(null)
                //navigate(R.id.payment_success_screen)
                paymentStatus = true
                val action = AllSubscriptionListDetailsFragmentDirections.actionScholarshipDetailsFragmentSucessToNavigationHome()
                findNavController().navigate(action)
            } else if((it.data?.data?.status == "failed") && !paymentStatus) {
                Log.d(TAG, "getOrderCreateStatusSubscriptionListResponse: failed")
                paymentStatusCheckHandler.removeCallbacksAndMessages(null)
                //navigate(R.id.payment_failed_screen)
                paymentStatus = true
                val action = AllSubscriptionListDetailsFragmentDirections.actionScholarshipDetailsFragmentFailToNavigationHome()
                findNavController().navigate(action)
            }

        }

        viewModel.getOrderCreateStatusSubscriptionListErrorMessage.observe(viewLifecycleOwner) {
            Log.d(TAG, "getOrderCreateStatusSubscriptionListResponse " )
        }

        binding?.proceedToPay?.setOnClickListener {
            Log.d(TAG, "proceedToPay: orderId: "+orderId)
            paymentStatus = false
            binding?.detailsContainer?.visibility = View.GONE
            binding?.paymentContainer?.visibility = View.GONE
            binding?.progressBarContainer?.visibility = View.VISIBLE
            orderId?.let { it1 -> viewModel.getOrderInitSubscriptionList(it1) }
        }

    }

    private fun showLoader(status:Int) {
        if(status == View.VISIBLE) {
            binding?.progressBarId?.visibility = View.VISIBLE
        } else {
            binding?.progressBarId?.visibility = View.GONE
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
        showLoader(View.GONE)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
        showLoader(View.GONE)
        binding?.progressBarContainer?.visibility = View.GONE
        binding?.paymentContainer?.visibility = View.GONE
        binding?.detailsContainer?.visibility = View.VISIBLE
        paymentStatusCheckHandler?.removeCallbacksAndMessages(null)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }
}