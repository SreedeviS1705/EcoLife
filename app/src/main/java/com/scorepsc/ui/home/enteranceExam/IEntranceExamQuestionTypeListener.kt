package com.scorepsc.ui.home.enteranceExam

import com.scorepsc.service.respose.homeBanner.enteranceExam.EnteranceExamQuestionTypeResponse

interface IEntranceExamQuestionTypeListener {
    fun clickEvent(it1: EnteranceExamQuestionTypeResponse.Datum)
    fun launchReport()

    fun launchAnswerKey(id: EnteranceExamQuestionTypeResponse.Datum?)
}