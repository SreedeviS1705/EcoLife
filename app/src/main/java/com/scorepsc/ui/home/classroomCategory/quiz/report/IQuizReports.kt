package com.scorepsc.ui.home.classroomCategory.quiz.report

import com.scorepsc.service.respose.homeBanner.quiz.report.QuizReportsResponse

interface IQuizReports {
    fun clickEvent(item: QuizReportsResponse.Datum?)
}