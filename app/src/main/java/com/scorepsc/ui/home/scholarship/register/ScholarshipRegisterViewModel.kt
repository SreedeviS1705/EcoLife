package com.scorepsc.ui.home.scholarship.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scorepsc.repository.StudentRepository
import com.scorepsc.service.Result
import com.scorepsc.service.request.ScholarshipUpdateEnrollmentRequest
import com.scorepsc.service.respose.homeBanner.scholarship.scholarshipCreateExam.scholarshipUpdateEnrollment.ScholarshipUpdateEnrollmentResponse
import com.scorepsc.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScholarshipRegisterViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "SRgtVModel"
    }

    private var _getScholarshipUpdateEnrollmentResponse = MutableLiveData<Result<ScholarshipUpdateEnrollmentResponse?>>()
    private var _getScholarshipUpdateEnrollmentErrorMessage = MutableLiveData<String?>()

    val getScholarshipUpdateEnrollmentResponse = _getScholarshipUpdateEnrollmentResponse
    val getScholarshipUpdateEnrollmentErrorMessage = _getScholarshipUpdateEnrollmentErrorMessage


    fun getScholarshipUpdateEnrollment(request:ScholarshipUpdateEnrollmentRequest) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                request.studId = studId
                studentRepository.getScholarshipUpdateEnrollment(token, request).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _getScholarshipUpdateEnrollmentResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _getScholarshipUpdateEnrollmentErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getScholarshipUpdateEnrollment else: ")
                        }
                    }
                }
            }.collect()
        }
    }


}