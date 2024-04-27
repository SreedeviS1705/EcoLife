package com.scorepsc.ui.home.classroomCategory.descriptiveQuesAns

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scorepsc.repository.StudentRepository
import com.scorepsc.service.Result
import com.scorepsc.service.request.ChapterWiseStudyNotesRequest
import com.scorepsc.service.respose.homeBanner.descrpQuestAns.DescrptQuestAnsResponse
import com.scorepsc.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DescrpQuesAnsViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "AnalyticsViewModel"
    }

    private var _descrptQuestAnsResponse = MutableLiveData<Result<DescrptQuestAnsResponse?>>()
    private var _descrptQuestAnsErrorMessage = MutableLiveData<String?>()

    val descrptQuestAnsResponse = _descrptQuestAnsResponse
    val descrptQuestAnsErrorMessage = _descrptQuestAnsErrorMessage

    fun getDescrpQuesAns(chapterId: String) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getDescrpQuesAns(token, ChapterWiseStudyNotesRequest(studId,chapterId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _descrptQuestAnsResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _descrptQuestAnsErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getAnalytics: ")
                        }
                    }
                }
            }.collect()
        }
    }

}