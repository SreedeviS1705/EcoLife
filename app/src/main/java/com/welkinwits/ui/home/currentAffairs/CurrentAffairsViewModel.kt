package com.welkinwits.ui.home.currentAffairs

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welkinwits.repository.StudentRepository
import com.welkinwits.service.Result
import com.welkinwits.service.request.StudentIdRequest
import com.welkinwits.service.respose.homeBanner.currentAfairs.CurrentAfairsResponse
import com.welkinwits.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentAffairsViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "CurrentAffairsViewModel"
    }

    private var _currentAfairsResponse = MutableLiveData<Result<CurrentAfairsResponse?>>()
    private var _currentAfairsErrorMessage = MutableLiveData<String?>()

    val currentAfairsResponse = _currentAfairsResponse
    val currentAfairsErrorMessage = _currentAfairsErrorMessage

    fun getCurrentAffairs() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getCurrentAffairs(token, StudentIdRequest(studId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _currentAfairsResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _currentAfairsErrorMessage.value = it.message
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