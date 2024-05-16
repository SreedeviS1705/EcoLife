package com.welkinwits.ui.home.enteranceExam

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welkinwits.repository.StudentRepository
import com.welkinwits.service.Result
import com.welkinwits.service.request.StudentIdRequest
import com.welkinwits.service.respose.homeBanner.enteranceExam.EnteranceExamQuestionTypeResponse
import com.welkinwits.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnteranceExamQuestionTypeViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "EnteranceExamQuestionTypeViewModel"
    }

    private var _enteranceExamQuestionTypeResponse = MutableLiveData<Result<EnteranceExamQuestionTypeResponse?>>()
    private var _enteranceExamQuestionTypeErrorMessage = MutableLiveData<String?>()

    val enteranceExamQuestionTypeResponse = _enteranceExamQuestionTypeResponse
    val enteranceExamQuestionTypeErrorMessage = _enteranceExamQuestionTypeErrorMessage

    fun getEnteranceQuestionTypeList() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getEnteranceQuestionTypeList(token, StudentIdRequest(studId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _enteranceExamQuestionTypeResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _enteranceExamQuestionTypeErrorMessage.value = it.message
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

    fun searchEmteranceExam(searchText:String) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.searchEnteranceExam(token, StudentIdRequest(studId), searchText).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _enteranceExamQuestionTypeResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _enteranceExamQuestionTypeErrorMessage.value = it.message
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