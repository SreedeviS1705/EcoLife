package com.scorepsc.ui.home.accadamicStreamSwitch

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scorepsc.repository.StudentRepository
import com.scorepsc.service.Result
import com.scorepsc.service.request.ChangeAcadamicStreamSwtichRequest
import com.scorepsc.service.request.StudentIdRequest
import com.scorepsc.service.respose.homeBanner.acadamicStreamSwitch.acadamicLevels.GetAcadamicLevelsResponse
import com.scorepsc.service.respose.homeBanner.acadamicStreamSwitch.changeAcadamicLevel.ChangeAcadamicLevelResponse
import com.scorepsc.util.DataStoreManager
import com.scorepsc.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AcademicLevelsViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "AcademicLevelsViewModel"
    }

    private var _getAcadamicLevelsResponse = MutableLiveData<Result<GetAcadamicLevelsResponse?>>()
    private var _getAcadamicLevelsErrorMessage = SingleLiveEvent<String?>()

    val getAcadamicLevelsResponse = _getAcadamicLevelsResponse
    val getAcadamicLevelsErrorMessage = _getAcadamicLevelsErrorMessage

    private var _changeAcadamicLevelResponse = MutableLiveData<Result<ChangeAcadamicLevelResponse?>>()
    private var _changeAcadamicLevelErrorMessage = SingleLiveEvent<String?>()

    val changeAcadamicLevelResponse = _changeAcadamicLevelResponse
    val changeAcadamicLevelErrorMessage = _changeAcadamicLevelErrorMessage

    fun getAcademicLevels() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getAcademicLevels(token, StudentIdRequest(studId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _getAcadamicLevelsResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _getAcadamicLevelsErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getSubjects: ")
                        }
                    }
                }
            }.collect()
        }
    }

    fun changeAcademicLevels(ac_level:Int) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.changeAcademicLevel(token, ChangeAcadamicStreamSwtichRequest(studId, ac_level)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _changeAcadamicLevelResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _changeAcadamicLevelErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getSubjects: ")
                        }
                    }
                }
            }.collect()
        }
    }

}