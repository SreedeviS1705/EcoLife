package com.welkinwits.ui.home.classroomCategory.quiz.report

import com.welkinwits.service.respose.homeBanner.quiz.report.QuizReportsResponse

interface IQuizReports {
    fun clickEvent(item: QuizReportsResponse.Datum?)
}