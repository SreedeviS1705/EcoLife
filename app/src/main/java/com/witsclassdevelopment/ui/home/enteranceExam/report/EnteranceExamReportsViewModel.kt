package com.witsclassdevelopment.ui.home.enteranceExam.report

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.witsclassdevelopment.repository.StudentRepository
import com.witsclassdevelopment.service.Result
import com.witsclassdevelopment.service.request.StudentIdRequest
import com.witsclassdevelopment.service.respose.homeBanner.enteranceExam.report.EnteranceExamReportsResponse
import com.witsclassdevelopment.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnteranceExamReportsViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "EnteranceExamReportsViewModel"
    }

    private var _enteranceEnteranceExamReportsResponse = MutableLiveData<Result<EnteranceExamReportsResponse?>>()
    private var _enteranceEnteranceExamReportsErrorMessage = MutableLiveData<String?>()

    val enteranceEnteranceExamReportsResponse = _enteranceEnteranceExamReportsResponse
    val enteranceEnteranceExamReportsErrorMessage = _enteranceEnteranceExamReportsErrorMessage

    fun getEnteranceReports() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getEnteranceExamReports(token, StudentIdRequest(studId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _enteranceEnteranceExamReportsResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _enteranceEnteranceExamReportsErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getEnteranceQuestionTypeList: ")
                        }
                    }
                }
            }.collect()
        }
    }

}