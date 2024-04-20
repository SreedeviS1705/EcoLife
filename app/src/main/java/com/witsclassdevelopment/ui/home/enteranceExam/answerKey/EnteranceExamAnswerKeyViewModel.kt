package com.witsclassdevelopment.ui.home.enteranceExam.answerKey

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.witsclassdevelopment.repository.StudentRepository
import com.witsclassdevelopment.service.Result
import com.witsclassdevelopment.service.request.EnteranceExamAnswerKeyRequest
import com.witsclassdevelopment.service.respose.homeBanner.enteranceExam.answerkey.EnteranceExamAnswerKeyResponse
import com.witsclassdevelopment.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnteranceExamAnswerKeyViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "EnteranceExamAnswerKeyViewModel"
    }

    private var _enteranceExamAnswerKeyResponse = MutableLiveData<Result<EnteranceExamAnswerKeyResponse?>>()
    private var _enteranceExamAnswerKeyErrorMessage = MutableLiveData<String?>()

    val enteranceExamAnswerKeyResponse = _enteranceExamAnswerKeyResponse
    val enteranceExamAnswerKeyErrorMessage = _enteranceExamAnswerKeyErrorMessage

    fun getEnteranceQuestionTypeList(examId:Int) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getEnteranceExamAnswerKey(token, EnteranceExamAnswerKeyRequest(studId, examId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _enteranceExamAnswerKeyResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _enteranceExamAnswerKeyErrorMessage.value = it.message
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