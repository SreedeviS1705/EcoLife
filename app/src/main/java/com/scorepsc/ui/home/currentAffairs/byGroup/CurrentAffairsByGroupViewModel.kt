package com.scorepsc.ui.home.currentAffairs.byGroup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scorepsc.repository.StudentRepository
import com.scorepsc.service.Result
import com.scorepsc.service.request.CurrentAffairByGroupRequest
import com.scorepsc.service.respose.homeBanner.currentAfairs.byGroup.CurrentAfairsByGroupResponse
import com.scorepsc.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentAffairsByGroupViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "CurrentAffairsByGroupViewModel"
    }

    private var _currentAfairsByGroupResponse = MutableLiveData<Result<CurrentAfairsByGroupResponse?>>()
    private var _currentAfairsByGroupErrorMessage = MutableLiveData<String?>()

    val currentAfairsByGroupResponse = _currentAfairsByGroupResponse
    val currentAfairsByGroupErrorMessage = _currentAfairsByGroupErrorMessage

    fun getCurrentAffairsByGroup(groupId:Int) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getCurrentAffairsByGroup(token, CurrentAffairByGroupRequest(studId, groupId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _currentAfairsByGroupResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _currentAfairsByGroupErrorMessage.value = it.message
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