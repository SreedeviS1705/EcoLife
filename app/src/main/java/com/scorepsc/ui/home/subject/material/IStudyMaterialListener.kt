package com.scorepsc.ui.home.subject.material

import com.scorepsc.service.respose.MaterialResponse

interface IStudyMaterialListener {
    fun clickEvent(subjectId: MaterialResponse.Data)
}