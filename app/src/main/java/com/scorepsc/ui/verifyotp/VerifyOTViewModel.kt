package com.scorepsc.ui.verifyotp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.scorepsc.repository.AuthRepository
import com.scorepsc.service.Result
import com.scorepsc.service.request.ResendOTPRequest
import com.scorepsc.service.request.VerifyOTPRequest
import com.scorepsc.service.respose.VerifyOTPResponse
import com.scorepsc.util.DataStoreManager
import com.scorepsc.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerifyOTViewModel @Inject constructor(
    private val authRepo: AuthRepository,
    private val dataStoreManager: DataStoreManager,
    application: Application
) : AndroidViewModel(application) {
    companion object {
        const val TAG = "VerifyOTViewModel"
    }


    private var _verifyOTPResponse = SingleLiveEvent<Result<VerifyOTPResponse?>>()
    private var _verifyOTPErrorMessage = SingleLiveEvent<String?>()

    val verifyOTPResponse = _verifyOTPResponse
    val verifyOTPErrorMessage = _verifyOTPErrorMessage

    fun verifyOTP(otpRef: Int?, otp: String) {
        viewModelScope.launch {
            val verifyOTPRequest = VerifyOTPRequest(otp, otpRef.toString())

            authRepo.verifyOTP(verifyOTPRequest).collect {
                when (it?.status) {
                    Result.Status.SUCCESS -> {
                        //it.data?.data?.studId?.let { dataStoreManager.setStudId(it) }
                        //it.data?.token?.let { dataStoreManager.setToken(it) }
                        _verifyOTPResponse.value = it
                    }
                    Result.Status.ERROR -> {
                        _verifyOTPErrorMessage.value = it.message
                    }
                    Result.Status.LOADING -> {
                        //Ignore
                    }
                    else -> {
                        Log.d(TAG, "verifyOTP: ")
                    }
                }

            }
        }
    }


    fun resendOTP(otpRef: Int?) {
        viewModelScope.launch {
            val resendOTPRequest = ResendOTPRequest(otpRef.toString())
            authRepo.resendOTP(resendOTPRequest).collect()
        }
    }
}