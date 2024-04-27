package com.scorepsc.ui.home.subject

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scorepsc.repository.StudentRepository
import com.scorepsc.service.Result
import com.scorepsc.service.request.StudentIdRequest
import com.scorepsc.service.respose.SubjectResponse
import com.scorepsc.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "SubjectViewModel"
    }

    private var _subjectResponse = MutableLiveData<Result<SubjectResponse?>>()
    private var _subjectErrorMessage = MutableLiveData<String?>()

    val subjectResponse = _subjectResponse
    val subjectErrorMessage = _subjectErrorMessage

    fun getSubjects() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.subjects(token, StudentIdRequest(studId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _subjectResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _subjectErrorMessage.value = it.message
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