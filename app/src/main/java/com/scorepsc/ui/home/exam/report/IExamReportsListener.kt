package com.scorepsc.ui.home.exam.report

import com.scorepsc.service.respose.homeBanner.exam.report.ExamReportResponse

interface IExamReportsListener {
    fun clickEvent(it1: ExamReportResponse.Datum)
}