package com.witsclassdevelopment.ui.home.classroomCategory.quiz.report

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.witsclassdevelopment.repository.StudentRepository
import com.witsclassdevelopment.service.Result
import com.witsclassdevelopment.service.request.QuizReportHistoryRequest
import com.witsclassdevelopment.service.respose.homeBanner.quiz.report.QuizReportsResponse
import com.witsclassdevelopment.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizReportsViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "QuizReportsViewModel"
    }

    private var _getQuizReportsResponse = MutableLiveData<Result<QuizReportsResponse?>>()
    private var _getQuizReportsErrorMessage = MutableLiveData<String?>()

    val getQuizReportsResponse = _getQuizReportsResponse
    val getQuizReportsErrorMessage = _getQuizReportsErrorMessage

    fun getQuizReports(chapterId: String) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getQuizReports(token, QuizReportHistoryRequest(studId,chapterId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _getQuizReportsResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _getQuizReportsErrorMessage.value = it.message
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