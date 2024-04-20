package com.witsclassdevelopment.ui.home.subject.demoClass

import com.witsclassdevelopment.service.respose.homeBanner.demoClassSubject.DemoClassWithSubjectResponse

interface IDemoClassSubjectListener {
    fun clickEvent(subjectId: DemoClassWithSubjectResponse.HomeBannerData)
}