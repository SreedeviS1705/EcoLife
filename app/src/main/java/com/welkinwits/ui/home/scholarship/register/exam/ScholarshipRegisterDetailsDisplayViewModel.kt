package com.welkinwits.ui.home.scholarship.register.exam

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welkinwits.repository.StudentRepository
import com.welkinwits.service.Result
import com.welkinwits.service.request.ScholarshipStartExamRequest
import com.welkinwits.service.request.scholarshipExamSubmit.ScholarshipExamSubmitRequest
import com.welkinwits.service.respose.homeBanner.scholarship.scholarshipCreateExam.scholarshipUpdateEnrollment.scholarshipStartExam.ScholarshipStartExamResponse
import com.welkinwits.service.respose.homeBanner.scholarship.scholarshipCreateExam.scholarshipUpdateEnrollment.scholarshipStartExam.submit.ScholarshipExamSubmitResponse
import com.welkinwits.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScholarshipRegisterDetailsDisplayViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "SRgtVModel"
    }

    private var _getScholarshipStartExamResponse = MutableLiveData<Result<ScholarshipStartExamResponse?>>()
    private var _getScholarshipStartExamErrorMessage = MutableLiveData<String?>()

    val getScholarshipStartExamResponse = _getScholarshipStartExamResponse
    val getScholarshipStartExamErrorMessage = _getScholarshipStartExamErrorMessage

    private var _getScholarshipExamSubmitResponse = MutableLiveData<Result<ScholarshipExamSubmitResponse?>>()
    private var _getScholarshipExamSubmitErrorMessage = MutableLiveData<String?>()

    val getScholarshipExamSubmitResponse = _getScholarshipExamSubmitResponse
    val getScholarshipExamSubmitErrorMessage = _getScholarshipExamSubmitErrorMessage


    fun getScholarshipStartExam(request:ScholarshipStartExamRequest) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                request.studId = studId
                studentRepository.getScholarshipStartExam(token, request).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _getScholarshipStartExamResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            if(it.message.equals("Hey, You have already Attended the Exam!")) {
                                _getScholarshipStartExamResponse.value = it
                            } else {
                                _getScholarshipStartExamErrorMessage.value = it.message
                            }
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

    fun getScholarshipExamSubmit(request:ScholarshipExamSubmitRequest) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                request.studId = studId
                studentRepository.getScholarshipExamSubmit(token, request).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _getScholarshipExamSubmitResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _getScholarshipExamSubmitErrorMessage.value = it.message
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