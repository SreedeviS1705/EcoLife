package com.witsclassdevelopment.ui.home.feePayment.feePaymentModeOfStudy.modeOfPayment.feeStructure.partPayment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.witsclassdevelopment.repository.StudentRepository
import com.witsclassdevelopment.service.Result
import com.witsclassdevelopment.service.request.PartPaymentListByPackageRequest
import com.witsclassdevelopment.service.request.PartPaymentListRequest
import com.witsclassdevelopment.service.respose.homeBanner.partPayment.PartPaymentListResponse
import com.witsclassdevelopment.util.DataStoreManager
import com.witsclassdevelopment.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PartPaymentListViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    companion object {
        const val TAG = "PartPaymentListViewModel"
    }
    private var _partPaymentListResponse = MutableLiveData<Result<PartPaymentListResponse?>>()
    private var _errorMessage = SingleLiveEvent<String?>()

    val partPaymentListResponse = _partPaymentListResponse
    val errorMessage = _errorMessage

    fun getPartPaymentList(packageId: String?) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.partPaymentList(token, PartPaymentListRequest(studId,Integer.parseInt(packageId))).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _partPaymentListResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _errorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getPartPaymentList: ")
                        }
                    }
                }
            }.collect()
        }
    }
    fun getPartPaymentPackageList(packageId: String?, enrollmentId: String?) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.partPaymentListBypackage(token, PartPaymentListByPackageRequest(studId,Integer.parseInt(packageId),
                    Integer.parseInt(enrollmentId))).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _partPaymentListResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _errorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getPartPaymentPackageList: ")
                        }
                    }
                }
            }.collect()
        }
    }

}