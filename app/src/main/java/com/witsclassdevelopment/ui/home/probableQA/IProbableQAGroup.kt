package com.witsclassdevelopment.ui.home.probableQA

import com.witsclassdevelopment.service.respose.homeBanner.probableQA.ProbableQAGroupResponse

interface IProbableQAGroup {
    fun clickEvent(id: ProbableQAGroupResponse.Datum)
}