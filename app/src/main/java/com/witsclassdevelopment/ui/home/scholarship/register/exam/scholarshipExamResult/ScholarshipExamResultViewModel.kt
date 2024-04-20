package com.witsclassdevelopment.ui.home.scholarship.register.exam.scholarshipExamResult

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.witsclassdevelopment.repository.StudentRepository
import com.witsclassdevelopment.service.Result
import com.witsclassdevelopment.service.request.scholarshipExamSubmit.ScholarshipRedeemRequest
import com.witsclassdevelopment.service.respose.homeBanner.scholarship.scholarshipCreateExam.scholarshipUpdateEnrollment.scholarshipStartExam.submit.scholarshipRedeem.ScholarshipRedeemResponse
import com.witsclassdevelopment.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScholarshipExamResultViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "ScholarshipExamResultViewModel"
    }

    private var _getScholarshipRedeemResponse = MutableLiveData<Result<ScholarshipRedeemResponse?>>()
    private var _getScholarshipRedeemErrorMessage = MutableLiveData<String?>()

    val getScholarshipRedeemResponse = _getScholarshipRedeemResponse
    val getScholarshipRedeemErrorMessage = _getScholarshipRedeemErrorMessage



    fun getScholarshipRedeem(request:ScholarshipRedeemRequest) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                request.studId = studId
                studentRepository.getScholarshipRedeem(token, request).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _getScholarshipRedeemResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _getScholarshipRedeemErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getScholarshipRedeem else: ")
                        }
                    }
                }
            }.collect()
        }
    }


}