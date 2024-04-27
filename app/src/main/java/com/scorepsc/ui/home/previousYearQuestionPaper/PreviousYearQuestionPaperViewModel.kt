package com.scorepsc.ui.home.previousYearQuestionPaper

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scorepsc.repository.StudentRepository
import com.scorepsc.service.Result
import com.scorepsc.service.request.StudentIdRequest
import com.scorepsc.service.respose.homeBanner.previousYearQuestionPapers.PreviousYearQuestionResponse
import com.scorepsc.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreviousYearQuestionPaperViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "PQPaperViewModel"
    }

    private var _previousYearQuestionResponse = MutableLiveData<Result<PreviousYearQuestionResponse?>>()
    private var _previousYearQuestionErrorMessage = MutableLiveData<String?>()
    val previousYearQuestionResponse = _previousYearQuestionResponse
    val previousYearQuestionErrorMessage = _previousYearQuestionErrorMessage

    fun getPreviousYearPaper() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getPreviousYearPaper(token, StudentIdRequest(studId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _previousYearQuestionResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _previousYearQuestionErrorMessage.value = it.message
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