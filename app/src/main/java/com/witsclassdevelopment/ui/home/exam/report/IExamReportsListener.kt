package com.witsclassdevelopment.ui.home.exam.report

import com.witsclassdevelopment.service.respose.homeBanner.exam.report.ExamReportResponse

interface IExamReportsListener {
    fun clickEvent(it1: ExamReportResponse.Datum)
}