package com.witsclassdevelopment.ui.home.scholarship

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.witsclassdevelopment.repository.StudentRepository
import com.witsclassdevelopment.service.Result
import com.witsclassdevelopment.service.request.ScholarshipCreateExamRequest
import com.witsclassdevelopment.service.request.ScholarshipExamsRequest
import com.witsclassdevelopment.service.respose.homeBanner.scholarship.ScholarshipExamsResponse
import com.witsclassdevelopment.service.respose.homeBanner.scholarship.scholarshipCreateExam.ScholarshipCreateExamResponse
import com.witsclassdevelopment.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScholarshipExamsListViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "ScholarshipExamsViewModel"
    }

    private var _getScholarshipExamsResponse = MutableLiveData<Result<ScholarshipExamsResponse?>>()
    private var _getScholarshipExamsErrorMessage = MutableLiveData<String?>()

    val getScholarshipExamsResponse = _getScholarshipExamsResponse
    val getScholarshipExamsErrorMessage = _getScholarshipExamsErrorMessage

    private var _getScholarshipCreateExamResponse = MutableLiveData<Result<ScholarshipCreateExamResponse?>>()
    private var _getScholarshipCreateExamErrorMessage = MutableLiveData<String?>()

    val getScholarshipCreateExamResponse = _getScholarshipCreateExamResponse
    val getScholarshipCreateExamErrorMessage = _getScholarshipCreateExamErrorMessage

    fun getScholarshipExamsList() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getScholarshipExamsList(token, ScholarshipExamsRequest(studId)).collect {
                    Log.d("DINKOOOOO", "token: "+token)
                    Log.d("DINKOOOOO", "studId: "+studId)
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _getScholarshipExamsResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _getScholarshipExamsErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getAnalytics: ")
                        }
                    }
                }
            }.collect()
        }
    }

    fun getScholarshipCreateExam(id: String?) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                if (id != null) {
                    studentRepository.getScholarshipCreateExam(token, ScholarshipCreateExamRequest(studId,id.toInt())).collect {
                        when (it?.status) {
                            Result.Status.SUCCESS -> {
                                _getScholarshipCreateExamResponse.value = it
                            }
                            Result.Status.ERROR -> {
                                _getScholarshipCreateExamErrorMessage.value = it.message
                            }
                            Result.Status.LOADING -> {
                                //Ignore
                            }
                            else -> {
                                Log.d(TAG, "getAnalytics: ")
                            }
                        }
                    }
                }
            }.collect()
        }
    }

}