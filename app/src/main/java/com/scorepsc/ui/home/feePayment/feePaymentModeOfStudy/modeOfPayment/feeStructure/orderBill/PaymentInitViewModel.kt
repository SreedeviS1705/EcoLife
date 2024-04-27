package com.scorepsc.ui.home.feePayment.feePaymentModeOfStudy.modeOfPayment.feeStructure.orderBill

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scorepsc.repository.StudentRepository
import com.scorepsc.service.CreateOrderRequest
import com.scorepsc.service.Result
import com.scorepsc.service.request.OrderStatusRequest
import com.scorepsc.service.request.PaymentInitRequest
import com.scorepsc.service.respose.homeBanner.createOrder.CreateOrderResponse
import com.scorepsc.service.respose.homeBanner.paymentInit.InitPaymentResponse
import com.scorepsc.service.respose.homeBanner.paymentStatus.OrderStatusResponse
import com.scorepsc.util.DataStoreManager
import com.scorepsc.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentInitViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "PaymentInitViewModel"
    }

    private var _initPaymentResponse = MutableLiveData<Result<InitPaymentResponse?>>()
    private var _errorMessage = SingleLiveEvent<String?>()

    val initPaymentResponse = _initPaymentResponse
    val errorMessage = _errorMessage

    private var _createOrderResponse = MutableLiveData<Result<CreateOrderResponse?>>()
    private var _createOrderErrorMessage = SingleLiveEvent<String?>()
    val createOrderResponse = _createOrderResponse
    val createOrderErrorMessage = _createOrderErrorMessage

    private var _paymentOrderStatusResponse = MutableLiveData<Result<OrderStatusResponse?>>()
    private var _paymentOrderStatusErrorMessage = SingleLiveEvent<String?>()

    val paymentOrderStatusResponse = _paymentOrderStatusResponse
    val paymentOrderStatusErrorMessage = _paymentOrderStatusErrorMessage

    fun paymentInit(orderId: String?) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.paymentInit(token, PaymentInitRequest(studId,Integer.parseInt(orderId))).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _initPaymentResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _errorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "paymentInit: ")
                        }
                    }
                }
            }.collect()
        }
    }

    fun createOrder(packageId: String?, enrollmentId: String?, partPaymentId: String?) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.createOrder(token, CreateOrderRequest(studId,Integer.parseInt(packageId),Integer.parseInt(enrollmentId),Integer.parseInt(partPaymentId))).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _createOrderResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _createOrderErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "createOrder: ")
                        }
                    }
                }
            }.collect()
        }
    }

    fun checkPaymentOrderStatus(orderId:String) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.checkOrderStatus(token, OrderStatusRequest(studId, Integer.parseInt(orderId))).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _paymentOrderStatusResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _paymentOrderStatusErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "checkPaymentOrderStatus: ")
                        }
                    }
                }
            }.collect()
        }
    }

}