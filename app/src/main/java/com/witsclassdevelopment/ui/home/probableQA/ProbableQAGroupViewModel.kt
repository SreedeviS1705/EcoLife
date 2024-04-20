package com.witsclassdevelopment.ui.home.probableQA

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.witsclassdevelopment.repository.StudentRepository
import com.witsclassdevelopment.service.Result
import com.witsclassdevelopment.service.request.StudentIdRequest
import com.witsclassdevelopment.service.respose.homeBanner.probableQA.ProbableQAGroupResponse
import com.witsclassdevelopment.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProbableQAGroupViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "NewsEventViewModel"
    }

    private var _probableQAGroupResponse = MutableLiveData<Result<ProbableQAGroupResponse?>>()
    private var _probableQAGroupErrorMessage = MutableLiveData<String?>()
    val probableQAGroupResponse = _probableQAGroupResponse
    val probableQAGroupErrorMessage = _probableQAGroupErrorMessage

    fun getProbableQAGroup() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getProbableQAGroup(token, StudentIdRequest(studId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _probableQAGroupResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _probableQAGroupErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getNews: ")
                        }
                    }
                }
            }.collect()
        }
    }

}