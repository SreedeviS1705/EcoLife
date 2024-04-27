package com.scorepsc.ui.home.classroomCategory.quiz

import com.scorepsc.service.respose.homeBanner.quiz.GetQuizQuestionResponse

interface IClassQuizExamList {
    fun clickEvent(item: GetQuizQuestionResponse.Datum?)
}