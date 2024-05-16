package com.welkinwits.ui.topic

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welkinwits.repository.StudentRepository
import com.welkinwits.service.Result
import com.welkinwits.service.request.TopicRequest
import com.welkinwits.service.request.WatchedListRequest
import com.welkinwits.service.respose.TopicResponse
import com.welkinwits.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopicViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "TopicViewModel"
    }

    private var _topicResponse = MutableLiveData<Result<TopicResponse?>>()
    private var _topicErrorMessage = MutableLiveData<String?>()

    val topicResponse = _topicResponse
    val topicErrorMessage = _topicErrorMessage

    fun getTopics(chapterId: String?) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getTopics(token, TopicRequest(studId, chapterId!!)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _topicResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _topicErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getTopics: ")
                        }
                    }
                }
            }.collect()
        }
    }

    fun addToWatchedList(topicId: String) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.addToWatchedList(
                    token,
                    WatchedListRequest(studId, topicId)
                ).collect()
            }.collect()
        }
    }

}