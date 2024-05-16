package com.welkinwits.ui.home.exam.modelExam

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welkinwits.repository.StudentRepository
import com.welkinwits.service.Result
import com.welkinwits.service.request.exams.SingleExamRequest
import com.welkinwits.service.request.exams.submitAction.ExamSubmitRequest
import com.welkinwits.service.respose.homeBanner.exam.singleExam.SingleExamResponce
import com.welkinwits.service.respose.homeBanner.exam.singleExam.submitAction.ExamSubmitResponse
import com.welkinwits.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleExamViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "SingleExamViewModel"
    }

    private var _singleExamResponse = MutableLiveData<Result<SingleExamResponce?>>()
    private var _singleExamErrorMessage = MutableLiveData<String?>()

    private var _singleExamSubmitAnswerResponse = MutableLiveData<Result<ExamSubmitResponse?>?>()
    private var _singleExamSubmitAnswerErrorMessage = MutableLiveData<String?>()


    val singleExamResponse = _singleExamResponse
    val singleExamErrorMessage = _singleExamErrorMessage

    val singleExamSubmitAnswerResponse = _singleExamSubmitAnswerResponse
    val singleExamSubmitAnswerErrorMessage = _singleExamSubmitAnswerErrorMessage

    fun getSingleExam(examId:Int) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getSingleExam(token, SingleExamRequest(studId,examId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _singleExamResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _singleExamErrorMessage.value = it.message
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

    fun submitAnswer(request:ExamSubmitRequest) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                request.studId = studId
                if(token == null || studId == null) return@combine
                studentRepository.submitExamAnswer(token, request).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _singleExamSubmitAnswerResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _singleExamSubmitAnswerErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "submitAnswer: ")
                        }
                    }
                }
            }.collect()
        }
    }

    fun clearSingleExamSubmitAnswerResponse() {
        _singleExamSubmitAnswerResponse.value = null
    }

}