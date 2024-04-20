package com.witsclassdevelopment.ui.home.classroomCategory

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.witsclassdevelopment.repository.StudentRepository
import com.witsclassdevelopment.service.Result
import com.witsclassdevelopment.service.request.ChapterWiseStudyNotesRequest
import com.witsclassdevelopment.service.respose.homeBanner.chapterWiseStudyNotes.ChapterWiseStudyNotesResponce
import com.witsclassdevelopment.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChapterWiseStudyNotesViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "AnalyticsViewModel"
    }

    private var _chapterWiseStudyResponse = MutableLiveData<Result<ChapterWiseStudyNotesResponce?>>()
    private var _chapterWiseStudyErrorMessage = MutableLiveData<String?>()

    val chapterWiseStudyResponse = _chapterWiseStudyResponse
    val chapterWiseStudyErrorMessage = _chapterWiseStudyErrorMessage

    fun getChapterWiseStudyNotes(chapterId: String) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getChapterWiseClassNotes(token, ChapterWiseStudyNotesRequest(studId,chapterId)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _chapterWiseStudyResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _chapterWiseStudyErrorMessage.value = it.message
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