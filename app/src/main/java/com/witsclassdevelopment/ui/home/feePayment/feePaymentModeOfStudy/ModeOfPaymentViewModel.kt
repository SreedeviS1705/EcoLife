package com.witsclassdevelopment.ui.home.feePayment.feePaymentModeOfStudy

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.witsclassdevelopment.repository.StudentRepository
import com.witsclassdevelopment.service.Result
import com.witsclassdevelopment.service.request.StudentIdRequest
import com.witsclassdevelopment.service.respose.homeBanner.feePayment.FeePaymentModeOfStudyResponse
import com.witsclassdevelopment.util.DataStoreManager
import com.witsclassdevelopment.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModeOfPaymentViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "ModeOfPaymentViewModel"
    }

    private var _modeOfPaymentResponse = MutableLiveData<Result<FeePaymentModeOfStudyResponse?>>()
    private var _errorMessage = SingleLiveEvent<String?>()

    val modeOfPaymentResponse = _modeOfPaymentResponse
    val errorMessage = _errorMessage

    fun getModeOfPayment() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.modeOfStudy(token, StudentIdRequest(studId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _modeOfPaymentResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _errorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getModeOfPayment: ")
                        }
                    }
                }
            }.collect()
        }
    }

}