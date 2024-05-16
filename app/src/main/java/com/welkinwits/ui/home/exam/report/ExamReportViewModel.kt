package com.welkinwits.ui.home.exam.report

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welkinwits.repository.StudentRepository
import com.welkinwits.service.Result
import com.welkinwits.service.request.StudentIdRequest
import com.welkinwits.service.respose.homeBanner.exam.report.ExamReportResponse
import com.welkinwits.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExamReportViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "ExamReportViewModel"
    }

    private var _examReportResponse = MutableLiveData<Result<ExamReportResponse?>>()
    private var _examReportErrorMessage = MutableLiveData<String?>()

    val examReportResponse = _examReportResponse
    val errorMessage = _examReportErrorMessage


    fun getExamReport() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getExamReport(token, StudentIdRequest(studId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _examReportResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _examReportErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getSingleExam: ")
                        }
                    }
                }
            }.collect()
        }
    }
}