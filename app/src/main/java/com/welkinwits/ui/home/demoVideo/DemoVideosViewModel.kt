package com.welkinwits.ui.home.demoVideo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welkinwits.repository.StudentRepository
import com.welkinwits.service.Result
import com.welkinwits.service.request.StudentIdRequest
import com.welkinwits.service.respose.homeBanner.demoVideos.DemoVideosResponse
import com.welkinwits.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DemoVideosViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "DemoVideosViewModel"
    }

    private var _demoVideosResponse = MutableLiveData<Result<DemoVideosResponse?>>()
    private var _demoVideosErrorMessage = MutableLiveData<String?>()

    val demoVideosResponse = _demoVideosResponse
    val demoVideosErrorMessage = _demoVideosErrorMessage

    fun getDemoVideos() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.demoVideos(token, StudentIdRequest(studId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _demoVideosResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _demoVideosErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getDemoVideos: ")}
                    }
                }
            }.collect()
        }
    }

}