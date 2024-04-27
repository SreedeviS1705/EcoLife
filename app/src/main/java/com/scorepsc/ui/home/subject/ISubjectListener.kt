package com.scorepsc.ui.home.subject

import com.scorepsc.service.respose.SubjectResponse

interface ISubjectListener {
    fun clickEvent(subjectId: SubjectResponse.SubjectData)
}