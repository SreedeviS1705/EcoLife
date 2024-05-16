package com.welkinwits.ui.home.feePayment.feePaymentModeOfStudy.modeOfPayment.feeStructure.orderBill

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import com.welkinwits.R
import com.welkinwits.databinding.FragmentPaymentOrderBillBinding
import com.welkinwits.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class PaymentOrderBillFragment : BaseFragment(R.layout.fragment_payment_order_bill) {

    companion object {
        const val TAG = "OrderBill"
    }

    private val viewModel: PaymentInitViewModel by viewModels()
    private var binding: FragmentPaymentOrderBillBinding? = null
    private var orderId:String?=null
    private val paymentStatusCheckHandler = Handler(Looper.getMainLooper())

    var runnable: Runnable?=null
    var paymentStatus = false


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPaymentOrderBillBinding.bind(view)
        val offerAmount = arguments?.getString("offerAmount")
        val packageName = arguments?.getString("packageName")
        val duration = arguments?.getString("duration")
        val packageId = arguments?.getString("packageId")
        val enrollmentId = arguments?.getString("enrollmentId")
        loadPaymentGateWayScreen(View.GONE)
        binding?.offerAmountText?.text = "Rs $offerAmount/- (GST inc)"
        binding?.packageNameText?.text = packageName
        binding?.durationText?.text = duration

        runnable = Runnable {
            Log.d(TAG, "run: ")
            orderId?.let { viewModel?.checkPaymentOrderStatus(it) }
            runnable?.let { paymentStatusCheckHandler.postDelayed(it, 4000) }
        }

        viewModel.createOrder(packageId, enrollmentId, "0")

        viewModel.createOrderResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "createOrderResponse: " + it.data?.message)
            orderId = it.data?.data?.id
        }

        viewModel.createOrderErrorMessage.observe(viewLifecycleOwner) {
            Log.d(TAG, "errorMessage: ")
        }

        viewModel.initPaymentResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "initPaymentResponse: " + it.data?.message)
            if(!paymentStatus) {
                loadPaymentGateWayScreen(View.VISIBLE)
                binding?.paymentViewScreen?.settings?.javaScriptEnabled = true
                val oToken: String? = it.data?.data?.authToken
                val bdOrderId: String? = it.data?.data?.bdOrderId
                Log.d(TAG, "oToken: $oToken bdOrderId $bdOrderId")
                val url = "https://futureplusacademy.com/newtest/FUTUREPLUS/Api/Payment/payment_screen?bdOrderId=$bdOrderId&authToken=$oToken"
                Log.d(TAG, ""+url)
                binding?.paymentViewScreen?.loadUrl(url)
                //binding?.paymentViewScreen?.loadUrl("https://futureplusacademy.com/newtest/FUTUREPLUS/Api/Payment/payment_screen?bdOrderId=OA5C19XTTDC7IK&authToken=OToken 086B8FA0C07556533D257A5BDFEFC2918C1A37E241266BECEEE710CE37EFD85D0DE3BDFC345166F4397E8EB1B3E39857F2408AB9B5C8FBD30E5E744CCB8D9D61EA6416101FCF491F6EC3D5E2A4C4D8F252673271CA91DCD80CF282584FE12B18D5F86FDAE78395A0D3F906C1C2A33FBAC340E978B1EC5F3A0147F4E3CD183C38A553DBBAE110C236B91B3FF79A2D0A1E31A439F276B31C4FA54E7F.4145535F55415431")
                orderId?.let { viewModel?.checkPaymentOrderStatus(it) }
                runnable?.let {
                    paymentStatusCheckHandler.postDelayed(runnable!!, 5000)
                }
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Log.d(TAG, "errorMessage: size: ")
        }

        binding?.makePaymentBtn?.setOnClickListener {
            paymentStatus = false
            viewModel.paymentInit(orderId)
        }

        binding?.paymentViewScreen?.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                view.loadUrl(url!!)
                return false
            }
        }


        viewModel.paymentOrderStatusResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "paymentOrderStatusResponse status: "+it.data?.data?.status)
            if((it.data?.data?.status == "SUCCESS") && !paymentStatus) {
                paymentStatusCheckHandler.removeCallbacksAndMessages(null)
                navigate(R.id.payment_success_screen)
                paymentStatus = true
            } else if((it.data?.data?.status == "FAILED") && !paymentStatus) {
                paymentStatusCheckHandler.removeCallbacksAndMessages(null)
                navigate(R.id.payment_failed_screen)
                paymentStatus = true
            }
        }

        viewModel.paymentOrderStatusErrorMessage.observe(viewLifecycleOwner) {
            Log.d(TAG, "paymentOrderStatusErrorMessage status: ")
            paymentStatusCheckHandler.removeCallbacksAndMessages(null)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    private fun loadPaymentGateWayScreen(view: Int) {
        if(view == View.VISIBLE) {
            binding?.orderIdScreen?.visibility = View.GONE
            binding?.paymentContainer?.visibility = View.VISIBLE
        } else {
            binding?.orderIdScreen?.visibility = View.VISIBLE
            binding?.paymentContainer?.visibility = View.GONE
        }

    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
        paymentStatusCheckHandler.removeCallbacksAndMessages(null)
    }

}