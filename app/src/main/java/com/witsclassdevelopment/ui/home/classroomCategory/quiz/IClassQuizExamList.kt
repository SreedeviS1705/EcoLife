package com.witsclassdevelopment.ui.home.classroomCategory.quiz

import com.witsclassdevelopment.service.respose.homeBanner.quiz.GetQuizQuestionResponse

interface IClassQuizExamList {
    fun clickEvent(item: GetQuizQuestionResponse.Datum?)
}