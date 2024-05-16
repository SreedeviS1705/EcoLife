package com.welkinwits.ui.home.enteranceExam.report.single

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welkinwits.repository.StudentRepository
import com.welkinwits.service.Result
import com.welkinwits.service.request.SingleEnteranceReportRequest
import com.welkinwits.service.respose.homeBanner.enteranceExam.report.singleReportResponse.EnteranceExamReportResponse
import com.welkinwits.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnteranceSingleExamReportsViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "ESERViewModel"
    }

    private var _enteranceExamSingleReportResponse = MutableLiveData<Result<EnteranceExamReportResponse?>>()
    private var _enteranceExamSingleReportErrorMessage = MutableLiveData<String?>()

    val enteranceExamSingleReportResponse = _enteranceExamSingleReportResponse
    val enteranceExamSingleReportErrorMessage = _enteranceExamSingleReportErrorMessage

    fun getSingleEnteranceExamReport(singleEnteranceReportRequest: SingleEnteranceReportRequest) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                singleEnteranceReportRequest.studId = studId
                studentRepository.getSingleEnteranceExamReport(token, singleEnteranceReportRequest).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _enteranceExamSingleReportResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _enteranceExamSingleReportErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getSingleEnteranceExamReport: ")
                        }
                    }
                }
            }.collect()
        }
    }

}