package com.scorepsc.ui.profile

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.scorepsc.model.StateDistrict
import com.scorepsc.repository.AuthRepository
import com.scorepsc.repository.StudentRepository
import com.scorepsc.service.Result
import com.scorepsc.service.request.StudentIdRequest
import com.scorepsc.service.request.UpdateProfileRequest
import com.scorepsc.service.respose.BaseResponse
import com.scorepsc.service.respose.CountryResponse
import com.scorepsc.service.respose.ProfileResponse
import com.scorepsc.util.DataStoreManager
import com.scorepsc.util.SingleLiveEvent
import com.scorepsc.util.Util
import com.scorepsc.util.Util.combineWith
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val authRepository: AuthRepository,
    private val dataStoreManager: DataStoreManager, application: Application,
    private val gson: Gson,
) : AndroidViewModel(application) {
    companion object {
        const val TAG = "ProfileViewModel"
    }

    private var _profileResponse = MutableLiveData<Result<ProfileResponse?>>()
    private var _profileErrorMessage = SingleLiveEvent<String?>()

    val profileResponse = _profileResponse
    val profileErrorMessage = _profileErrorMessage

    private var _profileUpdateResponse = SingleLiveEvent<Result<BaseResponse?>>()
    private var _profileUpdateErrorMessage = SingleLiveEvent<String?>()

    val profileUpdateResponse = _profileUpdateResponse
    val profileUpdateErrorMessage = _profileUpdateErrorMessage

    val stateDistrict = MutableLiveData<StateDistrict>()
    val updateProfileRequest: UpdateProfileRequest = UpdateProfileRequest()


    private var _countryResponse = MutableLiveData<Result<CountryResponse?>>()
    private var _countryErrorMessage = SingleLiveEvent<String?>()

    val countryResponse = _countryResponse
    val countryErrorMessage = _countryErrorMessage

    init {
        val stateAndDistrictJson =
            Util.loadJSONFromAsset(getApplication(), "states-and-districts.json")
        stateDistrict.value = gson.fromJson(stateAndDistrictJson, StateDistrict::class.java)
    }

    var response: LiveData<Pair<Result<CountryResponse?>?, Result<ProfileResponse?>?>> =
        countryResponse.combineWith(profileResponse) { country, profile ->
            Pair(country, profile)
        }

    fun getProfile() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if (token == null || studId == null) return@combine
                studentRepository.getProfile(token, StudentIdRequest(studId)).collect {

                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _profileResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _profileErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getProfile: ")
                        }
                    }

                }
            }.collect()
        }
    }

    fun updateProfile() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if (token == null || studId == null) return@combine
                updateProfileRequest.studId = studId
                studentRepository.updateProfile(token, updateProfileRequest).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _profileUpdateResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _profileUpdateErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "updateProfile: ")
                        }
                    }
                }
            }.collect()
        }
    }

    fun getCountry() {
        viewModelScope.launch {
            authRepository.country().collect {
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