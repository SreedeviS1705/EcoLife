package com.welkinwits.ui.home.subject.demoClass

import com.welkinwits.service.respose.homeBanner.demoClassSubject.DemoClassWithSubjectResponse

interface IDemoClassSubjectListener {
    fun clickEvent(subjectId: DemoClassWithSubjectResponse.HomeBannerData)
}