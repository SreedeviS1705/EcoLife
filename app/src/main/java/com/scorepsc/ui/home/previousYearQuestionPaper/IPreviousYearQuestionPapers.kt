package com.scorepsc.ui.home.previousYearQuestionPaper

import com.scorepsc.service.respose.homeBanner.previousYearQuestionPapers.PreviousYearQuestionResponse

interface IPreviousYearQuestionPapers {
    fun clickEvent(id: PreviousYearQuestionResponse.Datum)
}