package com.welkinwits.ui.home.classroomCategory.quiz.report

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welkinwits.repository.StudentRepository
import com.welkinwits.service.Result
import com.welkinwits.service.request.QuizReportHistoryRequest
import com.welkinwits.service.respose.homeBanner.quiz.report.QuizReportsResponse
import com.welkinwits.util.DataStoreManager
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