package com.witsclassdevelopment.ui.home.subject.material

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.witsclassdevelopment.repository.StudentRepository
import com.witsclassdevelopment.service.Result
import com.witsclassdevelopment.service.request.MaterialRequest
import com.witsclassdevelopment.service.respose.MaterialResponse
import com.witsclassdevelopment.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudyMaterialViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    companion object {
        const val TAG = "StudyMaterialViewModel"
    }

    private var _materialResponse = MutableLiveData<Result<MaterialResponse?>>()
    private var _materialErrorMessage = MutableLiveData<String?>()

    val materialResponse = _materialResponse
    val materialErrorMessage = _materialErrorMessage

    fun getMaterials(chapterId: String?) {
        viewModelScope.launch {
            dataStoreManager.token.combine(dataStoreManager.studId) { token, studId ->
                if(token == null || studId == null) return@combine
                studentRepository.getMaterials(token, MaterialRequest(studId, chapterId!!)).collect {
                    when (it?.status) {
                        Result.Status.SUCCESS -> {
                            _materialResponse.value = it
                        }
                        Result.Status.ERROR -> {
                            _materialErrorMessage.value = it.message
                        }
                        Result.Status.LOADING -> {
                            //Ignore
                        }
                        else -> {
                            Log.d(TAG, "getMaterials: ")
                        }
                    }
                }
            }.collect()
        }
    }

}