package com.welkinwits.ui.home.subject.material

import com.welkinwits.service.respose.MaterialResponse

interface IStudyMaterialListener {
    fun clickEvent(subjectId: MaterialResponse.Data)
}