package com.welkinwits.ui.home.enteranceExam

import com.welkinwits.service.respose.homeBanner.enteranceExam.EnteranceExamQuestionTypeResponse

interface IEntranceExamQuestionTypeListener {
    fun clickEvent(it1: EnteranceExamQuestionTypeResponse.Datum)
    fun launchReport()

    fun launchAnswerKey(id: EnteranceExamQuestionTypeResponse.Datum?)
}