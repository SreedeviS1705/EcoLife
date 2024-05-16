package com.welkinwits.ui.home.classroomCategory.quiz

import com.welkinwits.service.respose.homeBanner.quiz.GetQuizQuestionResponse

interface IClassQuizExamList {
    fun clickEvent(item: GetQuizQuestionResponse.Datum?)
}