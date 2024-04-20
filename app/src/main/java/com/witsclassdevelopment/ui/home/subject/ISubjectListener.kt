package com.witsclassdevelopment.ui.home.subject

import com.witsclassdevelopment.service.respose.SubjectResponse

interface ISubjectListener {
    fun clickEvent(subjectId: SubjectResponse.SubjectData)
}