package com.scorepsc.ui.home.jobupdates.jobUpdateDetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scorepsc.repository.StudentRepository
import com.scorepsc.service.Result
import com.scorepsc.service.request.JobDetailsRequest
import com.scorepsc.service.respose.homeBanner.jobupdates.details.JobUpdatesDetailsResponse
import com.scorepsc.util.DataStoreManager
import com.scorepsc.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobUpdateDetailsViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "JobUpdateDetailsViewModel"
    }

    private var _JobDetailsResponse = MutableLiveData<Result<JobUpdatesDetailsResponse?>>()
    private var _errorMessage = SingleLiveEvent<String?>()

    val jobUpdatesResponse = _JobDetailsResponse
    val errorMessage = _errorMessage

    fun getJobUpdateDetails() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.jobDetails(token, JobDetailsRequest(studId, 1)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _JobDetailsResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _errorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getJobUpdateDetails: ")
                        }
                    }
                }
            }.collect()
        }
    }

}