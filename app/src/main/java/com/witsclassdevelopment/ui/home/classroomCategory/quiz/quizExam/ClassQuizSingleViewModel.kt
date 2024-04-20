package com.witsclassdevelopment.ui.home.classroomCategory.quiz.quizExam

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.witsclassdevelopment.repository.StudentRepository
import com.witsclassdevelopment.service.Result
import com.witsclassdevelopment.service.request.QuizSingleRequest
import com.witsclassdevelopment.service.request.quiz.PostQuizAnswerRequest
import com.witsclassdevelopment.service.respose.homeBanner.quiz.singlequiz.GetSingleQuizResponse
import com.witsclassdevelopment.service.respose.homeBanner.quiz.singlequiz.answer.PostQuizAnswerResponce
import com.witsclassdevelopment.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassQuizSingleViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "QuizSingleVModel"
    }

    private var _getQuizSingleQuestionsResponse = MutableLiveData<Result<GetSingleQuizResponse?>>()
    private var _getQuizSingleQuestionsErrorMessage = MutableLiveData<String?>()

    val getQuizSingleQuestionsResponse = _getQuizSingleQuestionsResponse
    val getQuizSingleQuestionsErrorMessage = _getQuizSingleQuestionsErrorMessage

    private var _postQuizAnswerResponse = MutableLiveData<Result<PostQuizAnswerResponce?>>()
    private var _postQuizAnswerErrorMessage = MutableLiveData<String?>()

    val getPostQuizAnswerResponse = _postQuizAnswerResponse
    val getPostQuizAnswerErrorMessage = _postQuizAnswerErrorMessage

    fun postQuizAnswerSingle(request: PostQuizAnswerRequest) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                request.studId = studId
                if(token == null || studId == null) return@combine
                studentRepository.postQuizAnswerSingle(token, request).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _postQuizAnswerResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _postQuizAnswerErrorMessage.value = it.message
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

    fun getQuizSingleExamList(quizId: String) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getQuizSingleExamList(token, QuizSingleRequest(studId,quizId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _getQuizSingleQuestionsResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _getQuizSingleQuestionsErrorMessage.value = it.message
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