package com.witsclassdevelopment.ui.home.subject.material

import com.witsclassdevelopment.service.respose.MaterialResponse

interface IStudyMaterialListener {
    fun clickEvent(subjectId: MaterialResponse.Data)
}