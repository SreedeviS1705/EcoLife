package com.scorepsc.ui.help

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scorepsc.repository.AuthRepository
import com.scorepsc.repository.StudentRepository
import com.scorepsc.service.Result
import com.scorepsc.service.request.FeedbackRequest
import com.scorepsc.service.respose.BaseResponse
import com.scorepsc.service.respose.WhatsAppNumberResponse
import com.scorepsc.util.DataStoreManager
import com.scorepsc.util.SingleLiveEvent
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