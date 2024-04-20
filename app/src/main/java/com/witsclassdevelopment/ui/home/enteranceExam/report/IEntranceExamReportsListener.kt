package com.witsclassdevelopment.ui.home.enteranceExam.report

import com.witsclassdevelopment.service.respose.homeBanner.enteranceExam.report.EnteranceExamReportsResponse

interface IEntranceExamReportsListener {
    fun clickEvent(it1: EnteranceExamReportsResponse.Datum)
}