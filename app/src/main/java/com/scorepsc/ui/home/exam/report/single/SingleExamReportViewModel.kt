package com.scorepsc.ui.home.exam.report.single

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scorepsc.repository.StudentRepository
import com.scorepsc.service.Result
import com.scorepsc.service.request.SingleExamReportRequest
import com.scorepsc.service.respose.homeBanner.exam.report.single.SingleExamReportResponse
import com.scorepsc.ui.home.enteranceExam.report.single.EnteranceSingleExamReportsViewModel
import com.scorepsc.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleExamReportViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "SERViewModel"
    }

    private var _singleExamReportReportResponse = MutableLiveData<Result<SingleExamReportResponse?>>()
    private var _singleExamReportReportErrorMessage = MutableLiveData<String?>()

    val singleExamReportReportResponse = _singleExamReportReportResponse
    val singleExamReportReportErrorMessage = _singleExamReportReportErrorMessage

    fun getSingleExamReport(recordId: Int) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getSingleExamReport(token, SingleExamReportRequest(studId, recordId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _singleExamReportReportResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _singleExamReportReportErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(EnteranceSingleExamReportsViewModel.TAG, "getSingleExamReport: ")
                        }
                    }
                }
            }.collect()
        }
    }

}