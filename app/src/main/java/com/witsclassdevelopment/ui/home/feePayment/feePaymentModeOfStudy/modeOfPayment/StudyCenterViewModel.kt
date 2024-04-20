package com.witsclassdevelopment.ui.home.feePayment.feePaymentModeOfStudy.modeOfPayment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.witsclassdevelopment.repository.StudentRepository
import com.witsclassdevelopment.service.Result
import com.witsclassdevelopment.service.request.StudentIdRequest
import com.witsclassdevelopment.service.request.VerifyProcedeEnrollmentRequest
import com.witsclassdevelopment.service.respose.homeBanner.studyCenter.StudyCenterResponse
import com.witsclassdevelopment.service.respose.homeBanner.verifyProcedeEnrollment.VerifyProcedeEnrollmentResponse
import com.witsclassdevelopment.util.DataStoreManager
import com.witsclassdevelopment.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudyCenterViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "StudyCenterViewModel"
    }

    private var _studyCenterResponse = MutableLiveData<Result<StudyCenterResponse?>>()
    private var _errorMessage = SingleLiveEvent<String?>()

    private var _verifyProceedEnrollmentResponse = MutableLiveData<Result<VerifyProcedeEnrollmentResponse?>>()
    private var _verifyProceedEnrollmentErrorMessage = SingleLiveEvent<String?>()

    val modeOfPaymentResponse = _studyCenterResponse
    val errorMessage = _errorMessage

    val verifyProceedEnrollmentResponse = _verifyProceedEnrollmentResponse
    val verifyProceedEnrollmenterrorMessage = _verifyProceedEnrollmentErrorMessage

    fun getStudyCenter() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.studyCenter(token, StudentIdRequest(studId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _studyCenterResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _errorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getStudyCenter: ")
                        }
                    }
                }
            }.collect()
        }
    }

    fun getVerifyProceedEnrollment(modeId: String?, franchisesId: Int?) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.verifyProceedEnrollment(token, VerifyProcedeEnrollmentRequest(studId, franchisesId,Integer.parseInt(modeId))).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _verifyProceedEnrollmentResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _verifyProceedEnrollmentErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getVerifyProceedEnrollment: ")
                        }
                    }
                }
            }.collect()
        }
    }

}