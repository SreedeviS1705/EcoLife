package com.welkinwits.ui.home.subject.batch.liveClasses

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welkinwits.repository.StudentRepository
import com.welkinwits.service.Result
import com.welkinwits.service.request.liveclass.liveClass.LiveClassRequest
import com.welkinwits.service.respose.homeBanner.liveClass.liveclasses.LiveClassesResponse
import com.welkinwits.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LiveClassesViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "LiveClassesViewModel"
    }

    private var _liveClassResponse = MutableLiveData<Result<LiveClassesResponse?>>()
    private var _liveClassErrorMessage = MutableLiveData<String?>()

    val liveClassResponse = _liveClassResponse
    val liveClassErrorMessage = _liveClassErrorMessage

    fun getLiveBatch(request: LiveClassRequest) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if (studId != null) {
                    request.studId = studId
                }
                if (token == null || studId == null) return@combine
                studentRepository.getLiveClasses(token, request).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _liveClassResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _liveClassErrorMessage.value = it.message
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