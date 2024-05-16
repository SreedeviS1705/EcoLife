package com.welkinwits.ui.home.exam.report

import com.welkinwits.service.respose.homeBanner.exam.report.ExamReportResponse

interface IExamReportsListener {
    fun clickEvent(it1: ExamReportResponse.Datum)
}