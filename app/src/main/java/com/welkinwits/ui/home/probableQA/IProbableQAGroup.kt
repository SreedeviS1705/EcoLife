package com.welkinwits.ui.home.probableQA

import com.welkinwits.service.respose.homeBanner.probableQA.ProbableQAGroupResponse

interface IProbableQAGroup {
    fun clickEvent(id: ProbableQAGroupResponse.Datum)
}