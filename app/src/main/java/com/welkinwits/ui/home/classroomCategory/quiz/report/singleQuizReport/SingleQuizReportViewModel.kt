package com.welkinwits.ui.home.classroomCategory.quiz.report.singleQuizReport

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welkinwits.repository.StudentRepository
import com.welkinwits.service.Result
import com.welkinwits.service.request.quiz.SingleQuizReportRequest
import com.welkinwits.service.respose.homeBanner.quiz.report.singleQuizReport.SingleQuizReportResponse
import com.welkinwits.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleQuizReportViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "QuizReportsViewModel"
    }

    private var _getSingleQuizReportResponse = MutableLiveData<Result<SingleQuizReportResponse?>>()
    private var _getSingleQuizReportErrorMessage = MutableLiveData<String?>()

    val getSingleQuizReportResponse = _getSingleQuizReportResponse
    val getSingleQuizReportErrorMessage = _getSingleQuizReportErrorMessage

    fun getSingleQuizReport(request:SingleQuizReportRequest) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                request.studId = studId
                studentRepository.getSingleQuizReport(token, request).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _getSingleQuizReportResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _getSingleQuizReportErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getAnalytics: ")
                        }
                    }
                }
            }.collect()
        }
    }

}