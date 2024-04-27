package com.scorepsc.ui.home.flashCard.flashCardItems

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scorepsc.repository.StudentRepository
import com.scorepsc.service.Result
import com.scorepsc.service.request.FlashCardItemsRequest
import com.scorepsc.service.respose.homeBanner.flashCard.flashcards.FlashCardItemsResponse
import com.scorepsc.util.DataStoreManager
import com.scorepsc.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlashCardItemsViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "FlashCardItemsViewModel"
    }

    private var _flashCardItemsResponse = MutableLiveData<Result<FlashCardItemsResponse?>>()
    private var _errorMessage = SingleLiveEvent<String?>()

    val flashCardItemsResponse = _flashCardItemsResponse
    val errorMessage = _errorMessage

    fun getFlashCardItemsResponse(groupId:String) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.flashCardItemsResponse(token, FlashCardItemsRequest(studId, groupId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _flashCardItemsResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _errorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getJobUpdates: ")
                        }
                    }
                }
            }.collect()
        }
    }

}