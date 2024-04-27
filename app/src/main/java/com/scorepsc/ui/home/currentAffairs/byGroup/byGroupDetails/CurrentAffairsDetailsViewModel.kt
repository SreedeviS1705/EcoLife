package com.scorepsc.ui.home.currentAffairs.byGroup.byGroupDetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scorepsc.repository.StudentRepository
import com.scorepsc.service.Result
import com.scorepsc.service.request.CurrentAffairDetailsRequest
import com.scorepsc.service.respose.homeBanner.currentAfairs.byGroup.details.CurrentAfairsDetailsResponse
import com.scorepsc.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentAffairsDetailsViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "CurrentAffairsDetailsViewModel"
    }

    private var _currentAfairsdetailsResponse = MutableLiveData<Result<CurrentAfairsDetailsResponse?>>()
    private var _currentAfairsdetailsErrorMessage = MutableLiveData<String?>()

    val currentAfairsdetailsResponse = _currentAfairsdetailsResponse
    val currentAfairsdetailsErrorMessage = _currentAfairsdetailsErrorMessage

    fun getCurrentAffairsDetails(postId:Int) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getCurrentAffairsDetails(token, CurrentAffairDetailsRequest(studId, postId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _currentAfairsdetailsResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _currentAfairsdetailsErrorMessage.value = it.message
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