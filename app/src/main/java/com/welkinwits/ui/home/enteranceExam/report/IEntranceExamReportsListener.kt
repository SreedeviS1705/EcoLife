package com.welkinwits.ui.home.enteranceExam.report

import com.welkinwits.service.respose.homeBanner.enteranceExam.report.EnteranceExamReportsResponse

interface IEntranceExamReportsListener {
    fun clickEvent(it1: EnteranceExamReportsResponse.Datum)
}