package com.scorepsc.ui.home.enteranceExam.report

import com.scorepsc.service.respose.homeBanner.enteranceExam.report.EnteranceExamReportsResponse

interface IEntranceExamReportsListener {
    fun clickEvent(it1: EnteranceExamReportsResponse.Datum)
}