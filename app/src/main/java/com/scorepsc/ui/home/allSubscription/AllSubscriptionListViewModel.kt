package com.scorepsc.ui.home.allSubscription

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scorepsc.repository.StudentRepository
import com.scorepsc.service.Result
import com.scorepsc.service.request.StudentIdRequest
import com.scorepsc.service.respose.homeBanner.subscription.GetAllSubscriptionListResponse
import com.scorepsc.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllSubscriptionListViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "AllSubscriptionListViewModel"
    }

    private var _getAllSubscriptionListResponse = MutableLiveData<Result<GetAllSubscriptionListResponse?>>()
    private var _getAllSubscriptionListErrorMessage = MutableLiveData<String?>()

    val getAllSubscriptionListResponse = _getAllSubscriptionListResponse
    val getAllSubscriptionListErrorMessage = _getAllSubscriptionListErrorMessage

    fun getAllSubscriptionList() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getAllSubscriptionList(token, StudentIdRequest(studId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _getAllSubscriptionListResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _getAllSubscriptionListErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getAllSubscriptionList: ")
                        }
                    }
                }
            }.collect()
        }
    }

}