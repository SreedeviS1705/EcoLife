package com.scorepsc.ui.home.subject.demoClass

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scorepsc.repository.StudentRepository
import com.scorepsc.service.Result
import com.scorepsc.service.request.DemoClassRequest
import com.scorepsc.service.respose.homeBanner.demoClassSubject.DemoClassWithSubjectResponse
import com.scorepsc.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DemoClassSubjectViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "DemoClassSubjectViewModel"
    }

    private var _demoClassWithSubjectResponse = MutableLiveData<Result<DemoClassWithSubjectResponse?>>()
    private var _demoClassWithSubjectErrorMessage = MutableLiveData<String?>()

    val demoClassWithSubjectResponse = _demoClassWithSubjectResponse
    val demoClassWithSubjectErrorMessage = _demoClassWithSubjectErrorMessage

    fun getDemoClassSubject(subjectId:Int) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getDemoClassSubject(token, DemoClassRequest(studId, subjectId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _demoClassWithSubjectResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _demoClassWithSubjectErrorMessage.value = it.message
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