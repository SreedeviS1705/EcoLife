package com.witsclassdevelopment.ui.home.feePayment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.witsclassdevelopment.repository.StudentRepository
import com.witsclassdevelopment.service.Result
import com.witsclassdevelopment.service.request.StudentIdRequest
import com.witsclassdevelopment.service.respose.homeBanner.getActiveSubscription.GetActiveSubscriptionResponse
import com.witsclassdevelopment.util.DataStoreManager
import com.witsclassdevelopment.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeePaymentViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "FeePaymentViewModel"
    }

    private var _getActiveSubscriptionResponse = MutableLiveData<Result<GetActiveSubscriptionResponse?>>()
    private var _getActiveSubscriptionerrorMessage = SingleLiveEvent<String?>()

    val getActiveSubscriptionResponse = _getActiveSubscriptionResponse
    val getActiveSubscriptionerrorMessage = _getActiveSubscriptionerrorMessage

    fun getActiveSubscription() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getActiveSubscription(token, StudentIdRequest(studId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _getActiveSubscriptionResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _getActiveSubscriptionerrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getActiveSubscription: ")
                        }
                    }
                }
            }.collect()
        }
    }

}