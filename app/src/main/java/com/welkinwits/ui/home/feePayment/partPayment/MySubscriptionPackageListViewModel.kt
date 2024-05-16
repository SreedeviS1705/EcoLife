package com.welkinwits.ui.home.feePayment.partPayment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welkinwits.repository.StudentRepository
import com.welkinwits.service.Result
import com.welkinwits.service.request.StudentIdRequest
import com.welkinwits.service.respose.homeBanner.myPartPayment.MySubscriptionPackageListResponse
import com.welkinwits.util.DataStoreManager
import com.welkinwits.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MySubscriptionPackageListViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    companion object {
        const val TAG = "MySubscriptionPackageListViewModel"
    }
    private var _mySubscriptionPackageListPaymentResponse = MutableLiveData<Result<MySubscriptionPackageListResponse?>>()
    private var _mySubscriptionPackageListErrorMessage = SingleLiveEvent<String?>()

    val mySubscriptionPackageListResponse = _mySubscriptionPackageListPaymentResponse
    val mySubscriptionPackageListErrorMessage = _mySubscriptionPackageListErrorMessage

    fun getMySubscriptionPackageList() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getMySubscriptionPackages(token, StudentIdRequest(studId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _mySubscriptionPackageListPaymentResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _mySubscriptionPackageListErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getMySubscriptionPackageList: ")
                        }
                    }
                }
            }.collect()
        }
    }

}