package com.welkinwits.ui.home.importantLink

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welkinwits.repository.StudentRepository
import com.welkinwits.service.Result
import com.welkinwits.service.request.StudentIdRequest
import com.welkinwits.service.respose.homeBanner.importantLink.ImportantLinkResponse
import com.welkinwits.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImportantLinkViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "ImportantLinkViewModel"
    }

    private var _importantLinkResponse = MutableLiveData<Result<ImportantLinkResponse?>>()
    private var _importantLinkErrorMessage = MutableLiveData<String?>()
    val importantLinkResponse = _importantLinkResponse
    val importantLinkErrorMessage = _importantLinkErrorMessage

    fun getImportantLink() {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getImportantLink(token, StudentIdRequest(studId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _importantLinkResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _importantLinkErrorMessage.value = it.message
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

}