package com.scorepsc.ui.home.subject.demoClass

import com.scorepsc.service.respose.homeBanner.demoClassSubject.DemoClassWithSubjectResponse

interface IDemoClassSubjectListener {
    fun clickEvent(subjectId: DemoClassWithSubjectResponse.HomeBannerData)
}