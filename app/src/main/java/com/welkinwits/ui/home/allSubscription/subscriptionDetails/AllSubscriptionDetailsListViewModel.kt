package com.welkinwits.ui.home.allSubscription.subscriptionDetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welkinwits.repository.StudentRepository
import com.welkinwits.service.Result
import com.welkinwits.service.request.CreateOrderSubscriptionAllListRequest
import com.welkinwits.service.request.OrderCreateStatusSubscriptionListRequest
import com.welkinwits.service.request.OrderInitSubscriptionAllListRequest
import com.welkinwits.service.respose.homeBanner.subscription.createOrder.CreateOrderSubscriptionListResponse
import com.welkinwits.service.respose.homeBanner.subscription.createOrder.init.OrderInitSubscriptionListResponse
import com.welkinwits.service.respose.homeBanner.subscription.createOrder.statusCheck.OrderCreateStatusSubscriptionListResponse
import com.welkinwits.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllSubscriptionDetailsListViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "AllSubscriptionDetailsListViewModel"
    }

    private var _getCreateOrderSubscriptionListResponse = MutableLiveData<Result<CreateOrderSubscriptionListResponse?>>()
    private var _getCreateOrderSubscriptionListErrorMessage = MutableLiveData<String?>()

    val getCreateOrderSubscriptionListResponse = _getCreateOrderSubscriptionListResponse
    val getCreateOrderSubscriptionListErrorMessage = _getCreateOrderSubscriptionListErrorMessage

    private var _getOrderInitSubscriptionListResponse = MutableLiveData<Result<OrderInitSubscriptionListResponse?>>()
    private var _getOrderInitSubscriptionListErrorMessage = MutableLiveData<String?>()

    val getOrderInitSubscriptionListResponse = _getOrderInitSubscriptionListResponse
    val getOrderInitSubscriptionListErrorMessage = _getOrderInitSubscriptionListErrorMessage

    private var _getOrderCreateStatusSubscriptionListResponse = MutableLiveData<Result<OrderCreateStatusSubscriptionListResponse?>>()
    private var _getOrderCreateStatusSubscriptionListErrorMessage = MutableLiveData<String?>()

    val getOrderCreateStatusSubscriptionListResponse = _getOrderCreateStatusSubscriptionListResponse
    val getOrderCreateStatusSubscriptionListErrorMessage = _getOrderCreateStatusSubscriptionListErrorMessage

    fun getAllSubscriptionDetailsList(packageId:String) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getAllSubscriptionDetailsList(token, CreateOrderSubscriptionAllListRequest(studId, packageId.toInt())).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _getCreateOrderSubscriptionListResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _getCreateOrderSubscriptionListErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getAllSubscriptionList: ")
                        }
                    }
                }
            }.collect()
        }
    }

    fun getOrderInitSubscriptionList(orderId:Int) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getOrderInitSubscriptionList(token, OrderInitSubscriptionAllListRequest(studId, orderId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _getOrderInitSubscriptionListResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _getOrderInitSubscriptionListErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getAllSubscriptionList: ")
                        }
                    }
                }
            }.collect()
        }
    }

    fun getOrderStatusSubscriptionList(orderId:Int) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getOrderStatusSubscriptionList(token, OrderCreateStatusSubscriptionListRequest(studId, orderId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _getOrderCreateStatusSubscriptionListResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _getOrderCreateStatusSubscriptionListErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getAllSubscriptionList: ")
                        }
                    }
                }
            }.collect()
        }
    }

}