package com.scorepsc.ui.signup

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.scorepsc.model.StateDistrict
import com.scorepsc.repository.AuthRepository
import com.scorepsc.service.Result
import com.scorepsc.service.request.SignUpRequest
import com.scorepsc.service.respose.AcademicLevelResponse
import com.scorepsc.service.respose.CountryResponse
import com.scorepsc.service.respose.SignUpResponse
import com.scorepsc.util.SingleLiveEvent
import com.scorepsc.util.Util
import com.scorepsc.util.Util.loadJSONFromAsset
import com.google.gson.Gson
import com.scorepsc.service.request.RefferalCodeRequest
import com.scorepsc.service.respose.homeBanner.refferal.RefferalCodeResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepo: AuthRepository,
    private val gson: Gson,
    application: Application
) : AndroidViewModel(application) {
    companion object {
        const val TAG = "SignUpViewModel"
    }

    val stateDistrict = MutableLiveData<StateDistrict>()

    init {
        val stateAndDistrictJson = loadJSONFromAsset(getApplication(), "states-and-districts.json")
        stateDistrict.value = gson.fromJson(stateAndDistrictJson, StateDistrict::class.java)
    }

    val signUpRequest: SignUpRequest = SignUpRequest()

    private var _signUpResponse = SingleLiveEvent<Result<SignUpResponse?>>()
    private var _signUpErrorMessage = SingleLiveEvent<String?>()

    val signUpResponse = _signUpResponse
    val signUpErrorMessage = _signUpErrorMessage


    private var _academicLevelResponse = SingleLiveEvent<Result<AcademicLevelResponse?>>()
    private var _academicLevelErrorMessage = SingleLiveEvent<String?>()

    val academicLevelResponse = _academicLevelResponse
    val academicLevelErrorMessage = _academicLevelErrorMessage


    private var _countryResponse = MutableLiveData<Result<CountryResponse?>>()
    private var _countryErrorMessage = SingleLiveEvent<String?>()

    val countryResponse = _countryResponse
    val countryErrorMessage = _countryErrorMessage

    private var _refferalCodeResponse = MutableLiveData<Result<RefferalCodeResponse?>>()
    private var _refferalCodeErrorMessage = SingleLiveEvent<String?>()

    val refferalCodeResponse = _refferalCodeResponse
    val refferalCodeErrorMessage = _refferalCodeErrorMessage

    fun signUp() {
        viewModelScope.launch {
            signUpRequest.fcmId = Util.getFCMToken()
            signUpRequest.deviceId = Util.getDeviceId(getApplication())
            authRepo.signUp(signUpRequest).collect {
                when (it?.status) {
                    Result.Status.SUCCESS -> {
                        _signUpResponse.value = it
                    }
                    Result.Status.ERROR -> {
                        _signUpErrorMessage.value = it.message
                    }
                    Result.Status.LOADING -> {
                        //Ignore
                    }
                    else -> {
                        Log.d(TAG, "signUp: ")
                    }
                }

            }
        }
    }

    fun academicLevel() {
        viewModelScope.launch {
            authRepo.academicLevel().collect {
                when (it?.status) {
                    Result.Status.SUCCESS -> {
                        _academicLevelResponse.value = it
                    }
                    Result.Status.ERROR -> {
                        _academicLevelErrorMessage.value = it.message
                    }
                    Result.Status.LOADING -> {
                        //Ignore
                    }
                    else -> {
                        Log.d(TAG, "academicLevel: ")
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

    fun verrifyReferralCode(refferalCode:String) {
        viewModelScope.launch {
            authRepo.verrifyReferralCode(RefferalCodeRequest(refferalCode)).collect {
                when (it?.status) {
                    Result.Status.SUCCESS -> {
                        _refferalCodeResponse.value = it
                    }
                    Result.Status.ERROR -> {
                        _refferalCodeErrorMessage.value = it.message
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