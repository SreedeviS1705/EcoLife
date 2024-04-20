package com.witsclassdevelopment.ui.home.feePayment.feePaymentModeOfStudy.modeOfPayment.feeStructure

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.witsclassdevelopment.repository.StudentRepository
import com.witsclassdevelopment.service.Result
import com.witsclassdevelopment.service.request.FeeStructureRequest
import com.witsclassdevelopment.service.respose.homeBanner.feeStructure.FeeStructureResponse
import com.witsclassdevelopment.util.DataStoreManager
import com.witsclassdevelopment.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeeStructureViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "FeeStructureViewModel"
    }

    private var _feeStructureResponse = MutableLiveData<Result<FeeStructureResponse?>>()
    private var _errorMessage = SingleLiveEvent<String?>()

    val feeStructureResponse = _feeStructureResponse
    val errorMessage = _errorMessage

    fun getFeeStructure(modeId: String?) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.feeStructure(token, FeeStructureRequest(studId,Integer.parseInt(modeId))).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _feeStructureResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _errorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getFeeStructure: ")
                        }
                    }
                }
            }.collect()
        }
    }

}