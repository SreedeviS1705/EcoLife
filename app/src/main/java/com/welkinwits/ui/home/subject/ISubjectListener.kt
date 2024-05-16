package com.welkinwits.ui.home.subject

import com.welkinwits.service.respose.SubjectResponse

interface ISubjectListener {
    fun clickEvent(subjectId: SubjectResponse.SubjectData)
}