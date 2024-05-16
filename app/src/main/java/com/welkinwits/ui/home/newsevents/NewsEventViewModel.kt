package com.welkinwits.ui.home.newsevents

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welkinwits.repository.StudentRepository
import com.welkinwits.service.Result
import com.welkinwits.service.request.StudentIdRequest
import com.welkinwits.service.respose.NewsResponse
import com.welkinwits.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsEventViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "NewsEventViewModel"
    }

    private var _newsResponse = MutableLiveData<Result<NewsResponse?>>()
    private var _newsErrorMessage = MutableLiveData<String?>()
    val newsResponse = _newsResponse
    val newsErrorMessage = _newsErrorMessage

    fun getNews() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.news(token, StudentIdRequest(studId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _newsResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _newsErrorMessage.value = it.message
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