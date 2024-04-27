package com.scorepsc.ui.home.probableQA

import com.scorepsc.service.respose.homeBanner.probableQA.ProbableQAGroupResponse

interface IProbableQAGroup {
    fun clickEvent(id: ProbableQAGroupResponse.Datum)
}