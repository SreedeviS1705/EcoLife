package com.welkinwits.ui.signin

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.welkinwits.repository.AuthRepository
import com.welkinwits.service.Result
import com.welkinwits.service.Result.Status
import com.welkinwits.service.request.SignInRequest
import com.welkinwits.service.respose.CountryResponse
import com.welkinwits.service.respose.SignInResponse
import com.welkinwits.util.SingleLiveEvent
import com.welkinwits.util.Util.getDeviceId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepo: AuthRepository,
    application: Application
) : AndroidViewModel(application) {
    companion object {
        const val TAG = "SignInViewModel"
    }

    private var _signInResponse = SingleLiveEvent<com.welkinwits.service.Result<SignInResponse?>>()
    private var _signInErrorMessage = SingleLiveEvent<String?>()

    val signInResponse = _signInResponse
    val signInErrorMessage = _signInErrorMessage

    private var _countryResponse = SingleLiveEvent<Result<CountryResponse?>>()
    private var _countryErrorMessage = SingleLiveEvent<String?>()

    val countryResponse = _countryResponse
    val countryErrorMessage = _countryErrorMessage


    fun signIn(mobile: String, phoneCode: String) {
        viewModelScope.launch {

            val signInRequest = SignInRequest()
            signInRequest.mobile = mobile
        //    signInRequest.fcmId = getFCMToken()
            signInRequest.deviceId = getDeviceId(getApplication())
            signInRequest.phoneCode = phoneCode


            authRepo.signIn(signInRequest).collect {
                when (it?.status) {
                    Status.SUCCESS -> {
                        _signInResponse.value = it
                    }
                    Status.ERROR -> {
                        _signInErrorMessage.value = it.message
                    }
                    Status.LOADING -> {
                        //Ignore
                    }
                    else -> {
                        Log.d(TAG, "signIn: ")
                    }
                }
            }
        }
    }

    fun getCountry() {
        viewModelScope.launch {
            authRepo.country().collect {
                when (it?.status) {
                    Result.Status.SUCCESS -> {
                        _countryResponse.value = it
                    }
                    Result.Status.ERROR -> {
                        _countryErrorMessage.value = it.message
                    }
                    Result.Status.LOADING -> {
                        //Ignore
                    }
                    else -> {
                        Log.d(TAG, "getCountry: ")
                    }
                }

            }
        }
    }

}