package com.witsclassdevelopment.ui.help

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.witsclassdevelopment.repository.AuthRepository
import com.witsclassdevelopment.repository.StudentRepository
import com.witsclassdevelopment.service.Result
import com.witsclassdevelopment.service.request.FeedbackRequest
import com.witsclassdevelopment.service.respose.BaseResponse
import com.witsclassdevelopment.service.respose.WhatsAppNumberResponse
import com.witsclassdevelopment.util.DataStoreManager
import com.witsclassdevelopment.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HelpViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val authRepo: AuthRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "HelpViewModel"
    }

    private var _feedbackResponse = SingleLiveEvent<Result<BaseResponse?>>()
    private var _feedbackErrorMessage = SingleLiveEvent<String?>()

    val feedbackResponse = _feedbackResponse
    val feedbackErrorMessage = _feedbackErrorMessage

    private var _whatsAppNumberResponse = SingleLiveEvent<Result<WhatsAppNumberResponse?>>()
    private var _whatsAppNumberErrorMessage = SingleLiveEvent<String?>()

    val whatsAppNumberResponse = _whatsAppNumberResponse
    val whatsAppNumberErrorMessage = _whatsAppNumberErrorMessage

    fun submitFeedback(message: String) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.addFeedback(token, FeedbackRequest(message, studId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _feedbackResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _feedbackErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "submitFeedback: ")
                        }
                    }
                }
            }.collect()
        }
    }

    fun whatsAppNumber() {
        viewModelScope.launch {
            authRepo.whatsAppNumber().collect {
                when (it?.status) {
                    Result.Status.SUCCESS -> {
                        _whatsAppNumberResponse.value = it
                    }
                    Result.Status.ERROR -> {
                        _whatsAppNumberErrorMessage.value = it.message
                    }
                    Result.Status.LOADING -> {
                        //Ignore
                    }
                    else -> {
                        Log.d(TAG, "whatsAppNumber: ")
                    }
                }

            }
        }
    }
}