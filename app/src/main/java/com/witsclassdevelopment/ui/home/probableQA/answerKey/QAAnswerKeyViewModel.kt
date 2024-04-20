package com.witsclassdevelopment.ui.home.probableQA.answerKey

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.witsclassdevelopment.repository.StudentRepository
import com.witsclassdevelopment.service.Result
import com.witsclassdevelopment.service.request.QAAnswerKeyRequest
import com.witsclassdevelopment.service.respose.homeBanner.probableQA.qaAnswerKey.QAAnswerKeyResponse
import com.witsclassdevelopment.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QAAnswerKeyViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "QAAnswerKeyViewModel"
    }

    private var _qaAnswerKeyResponse = MutableLiveData<Result<QAAnswerKeyResponse?>>()
    private var _qaAnswerKeyErrorMessage = MutableLiveData<String?>()
    val qaAnswerKeyResponse = _qaAnswerKeyResponse
    val qaAnswerKeyErrorMessage = _qaAnswerKeyErrorMessage

    fun getQAAnswerKey(groupId:Int) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getQAAnswerKey(token, QAAnswerKeyRequest(studId, groupId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _qaAnswerKeyResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _qaAnswerKeyErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getNews: ")
                        }
                    }
                }
            }.collect()
        }
    }

}