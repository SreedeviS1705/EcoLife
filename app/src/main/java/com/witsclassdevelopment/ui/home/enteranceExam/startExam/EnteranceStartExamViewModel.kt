package com.witsclassdevelopment.ui.home.enteranceExam.startExam

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.witsclassdevelopment.repository.StudentRepository
import com.witsclassdevelopment.service.Result
import com.witsclassdevelopment.service.request.EnteranceStartExamRequest
import com.witsclassdevelopment.service.request.enteranceExam.EnteranceExamSubmitRequest
import com.witsclassdevelopment.service.respose.homeBanner.enteranceExam.startExam.EnteranceExamStartExamResponse
import com.witsclassdevelopment.service.respose.homeBanner.scholarship.scholarshipCreateExam.scholarshipUpdateEnrollment.scholarshipStartExam.submit.ScholarshipExamSubmitResponse
import com.witsclassdevelopment.ui.home.scholarship.register.exam.ScholarshipRegisterDetailsDisplayViewModel
import com.witsclassdevelopment.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnteranceStartExamViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "EnteranceStartExamViewModel"
    }

    private var _enteranceExamStartExamResponse = MutableLiveData<Result<EnteranceExamStartExamResponse?>>()
    private var _enteranceExamStartExamErrorMessage = MutableLiveData<String?>()

    val enteranceExamStartExamResponse = _enteranceExamStartExamResponse
    val enteranceExamStartExamErrorMessage = _enteranceExamStartExamErrorMessage

    private var _getEnteranceExamSubmitResponse = MutableLiveData<Result<ScholarshipExamSubmitResponse?>>()
    private var _getEnteranceExamSubmitErrorMessage = MutableLiveData<String?>()

    val getEnteranceExamSubmitResponse = _getEnteranceExamSubmitResponse
    val getEnteranceExamSubmitErrorMessage = _getEnteranceExamSubmitErrorMessage

    fun getEnteranceStartExam(exam_id:Int) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getEnteranceStartExam(token, EnteranceStartExamRequest(studId,exam_id)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _enteranceExamStartExamResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _enteranceExamStartExamErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getEnteranceStartExam: ")
                        }
                    }
                }
            }.collect()
        }
    }

    fun getEnteranceExamSubmit(request: EnteranceExamSubmitRequest) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                request.studId = studId
                studentRepository.getEnteranceExamSubmit(token, request).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _getEnteranceExamSubmitResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _getEnteranceExamSubmitErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(ScholarshipRegisterDetailsDisplayViewModel.TAG, "getEnteranceExamSubmit else: ")
                        }
                    }
                }
            }.collect()
        }
    }

}