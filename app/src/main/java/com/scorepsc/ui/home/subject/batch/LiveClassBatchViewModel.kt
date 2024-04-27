package com.scorepsc.ui.home.subject.batch

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scorepsc.repository.StudentRepository
import com.scorepsc.service.Result
import com.scorepsc.service.request.liveclass.batch.BatchRequest
import com.scorepsc.service.respose.homeBanner.liveClass.batch.LiveClassBatchResponse
import com.scorepsc.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LiveClassBatchViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "LiveClassBatchViewModel"
    }

    private var _batchResponse = MutableLiveData<Result<LiveClassBatchResponse?>>()
    private var _batchErrorMessage = MutableLiveData<String?>()

    val batchResponse = _batchResponse
    val batchErrorMessage = _batchErrorMessage

    fun getLiveBatch(request: BatchRequest) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if (studId != null) {
                    request.studId = studId
                }
                if (token == null || studId == null) return@combine
                studentRepository.getLiveClassBatch(token, request).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _batchResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _batchErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getLiveBatch: ")
                        }
                    }
                }
            }.collect()
        }
    }

}