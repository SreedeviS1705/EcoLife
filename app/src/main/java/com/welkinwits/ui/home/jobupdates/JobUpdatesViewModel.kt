package com.welkinwits.ui.home.jobupdates

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welkinwits.repository.StudentRepository
import com.welkinwits.service.Result
import com.welkinwits.service.request.StudentIdRequest
import com.welkinwits.service.respose.homeBanner.jobupdates.JobUpdatesResponse
import com.welkinwits.util.DataStoreManager
import com.welkinwits.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobUpdatesViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "JobUpdatesViewModel"
    }

    private var _jobUpdatesResponse = MutableLiveData<Result<JobUpdatesResponse?>>()
    private var _errorMessage = SingleLiveEvent<String?>()

    val jobUpdatesResponse = _jobUpdatesResponse
    val errorMessage = _errorMessage

    fun getJobUpdates() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.jobUpdates(token, StudentIdRequest(studId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _jobUpdatesResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _errorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getJobUpdates: ")
                        }
                    }
                }
            }.collect()
        }
    }

}