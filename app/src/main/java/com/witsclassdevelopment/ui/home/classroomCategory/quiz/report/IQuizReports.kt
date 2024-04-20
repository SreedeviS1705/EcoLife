package com.witsclassdevelopment.ui.home.classroomCategory.quiz.report

import com.witsclassdevelopment.service.respose.homeBanner.quiz.report.QuizReportsResponse

interface IQuizReports {
    fun clickEvent(item: QuizReportsResponse.Datum?)
}