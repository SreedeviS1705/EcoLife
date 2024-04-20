package com.witsclassdevelopment.ui.home.feePayment.paymentHistory

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.witsclassdevelopment.repository.StudentRepository
import com.witsclassdevelopment.service.Result
import com.witsclassdevelopment.service.request.PaymentHistoryRequest
import com.witsclassdevelopment.service.respose.homeBanner.paymentHistory.GetPaymentHistoryResponse
import com.witsclassdevelopment.util.DataStoreManager
import com.witsclassdevelopment.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentHistoryViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "PaymentHistoryViewModel"
    }

    private var _getPaymentHistoryResponse = MutableLiveData<Result<GetPaymentHistoryResponse?>>()
    private var _getPaymentHistoryerrorMessage = SingleLiveEvent<String?>()

    val getPaymentHistoryResponse = _getPaymentHistoryResponse
    val getPaymentHistoryerrorMessage = _getPaymentHistoryerrorMessage

    fun getPayMentHistory() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getPaymentHistory(token, PaymentHistoryRequest(studId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _getPaymentHistoryResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _getPaymentHistoryerrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getPayMentHistory: ")
                        }
                    }
                }
            }.collect()
        }
    }

}