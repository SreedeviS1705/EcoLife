package com.welkinwits.ui.home.classroomCategory.quiz

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welkinwits.repository.StudentRepository
import com.welkinwits.service.Result
import com.welkinwits.service.request.ChapterWiseStudyNotesRequest
import com.welkinwits.service.respose.homeBanner.quiz.GetQuizQuestionResponse
import com.welkinwits.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassQuizQuestionsViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "AnalyticsViewModel"
    }

    private var _getQuizQuestionsResponse = MutableLiveData<Result<GetQuizQuestionResponse?>>()
    private var _getQuizQuestionsErrorMessage = MutableLiveData<String?>()

    val getQuizQuestionsResponse = _getQuizQuestionsResponse
    val getQuizQuestionsErrorMessage = _getQuizQuestionsErrorMessage

    fun getQuizExamList(chapterId: String) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getQuizExamList(token, ChapterWiseStudyNotesRequest(studId,chapterId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _getQuizQuestionsResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _getQuizQuestionsErrorMessage.value = it.message
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