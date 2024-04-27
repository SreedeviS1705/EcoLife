package com.scorepsc.ui.chapter

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scorepsc.repository.StudentRepository
import com.scorepsc.service.Result
import com.scorepsc.service.request.ChapterRequest
import com.scorepsc.service.respose.ChapterResponse
import com.scorepsc.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChapterViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "ChapterViewModel"
    }

    private var _chapterResponse = MutableLiveData<Result<ChapterResponse?>>()
    private var _chapterErrorMessage = MutableLiveData<String?>()

    val chapterResponse = _chapterResponse
    val chapterErrorMessage = _chapterErrorMessage

    fun getChapters(subID: String?) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getChapters(token, ChapterRequest(studId, subID!!)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _chapterResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _chapterErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getChapters: ")
                        }
                    }
                }
            }.collect()
        }
    }

}