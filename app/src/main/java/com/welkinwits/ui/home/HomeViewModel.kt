package com.welkinwits.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welkinwits.repository.StudentRepository
import com.welkinwits.service.Result
import com.welkinwits.service.request.HomeBannerRequest
import com.welkinwits.service.request.StudentIdRequest
import com.welkinwits.service.respose.NewsResponse
import com.welkinwits.service.respose.SubjectResponse
import com.welkinwits.service.respose.homeBanner.HomeBannerResponse
import com.welkinwits.service.respose.homeBanner.getActiveSubscription.GetActiveSubscriptionResponse
import com.welkinwits.service.respose.homeBanner.latestUpdates.LatestUpdateResponse
import com.welkinwits.util.DataStoreManager
import com.welkinwits.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object{
        const val TAG = "HomeViewModel"
    }

    private var _subjectResponse = MutableLiveData<Result<SubjectResponse?>>()
    private var _homeBannerResponse = MutableLiveData<Result<HomeBannerResponse?>>()
    private var _LatestUpdateResponse = MutableLiveData<Result<LatestUpdateResponse?>>()
    private var _errorMessage = SingleLiveEvent<String?>()
    private var _errorMessageLatestUpdate = SingleLiveEvent<String?>()

    val subjectResponse = _subjectResponse
    val homeBannerResponse = _homeBannerResponse
    val errorMessage = _errorMessage
    val latestUpdateResponse = _LatestUpdateResponse
    val errorMessageLatestUpdate = _errorMessageLatestUpdate

    private var _newsResponse = MutableLiveData<Result<NewsResponse?>>()
    private var _newsErrorMessage = MutableLiveData<String?>()

    val newsResponse = _newsResponse
    val newsErrorMessage = _newsErrorMessage

    private var _getActiveSubscriptionResponse = MutableLiveData<Result<GetActiveSubscriptionResponse?>>()
    private var _getActiveSubscriptionerrorMessage = SingleLiveEvent<String?>()

    val getActiveSubscriptionResponse = _getActiveSubscriptionResponse
    val getActiveSubscriptionerrorMessage = _getActiveSubscriptionerrorMessage

    private var _getUserName = MutableLiveData<String?>()
    val getUserName = _getUserName

    fun getSubjects() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.subjects(token, StudentIdRequest(studId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _subjectResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _errorMessage.value = it.message
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

    fun getHomeBanner() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                Log.d(
                    TAG,
                    "getHomeBanner: token: $token studId: $studId"
                )
                if(token == null || studId == null) return@combine
                studentRepository.homeBanner(token, HomeBannerRequest(studId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _homeBannerResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _errorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getHomeBanner: ")
                        }
                    }
                }
            }.collect()
        }
    }

    fun getNews() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.news(token, StudentIdRequest(studId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _newsResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _newsErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getNews: ")
                        }
                    }
                }
            }.collect()
        }
    }

    fun getScrollUpdates() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getScrollUpdates(token, StudentIdRequest(studId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _LatestUpdateResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _errorMessageLatestUpdate.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getScrollUpdates: ")
                        }
                    }
                }
            }.collect()
        }
    }

    fun getActiveSubscription() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getActiveSubscription(token, StudentIdRequest(studId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _getActiveSubscriptionResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _getActiveSubscriptionerrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getActiveSubscription: ")
                        }
                    }
                }
            }.collect()
        }
    }

    fun getWishMessage(): String {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)

        return when (hour) {
            in 0..11 -> "Good morning"
            in 12..17 -> "Good afternoon"
            in 18..23 -> "Good evening"
            else -> "Hello"
        }
    }

     fun getUserName() {
         viewModelScope.launch {
             dataStoreManager.token.combine(dataStoreManager.name) { token, studName ->
                 Log.d(TAG, "getUserName: "+studName)
                 _getUserName.value = studName
             }.collect()
         }
    }

}