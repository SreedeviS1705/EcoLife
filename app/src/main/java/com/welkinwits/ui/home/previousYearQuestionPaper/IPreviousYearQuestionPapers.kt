package com.welkinwits.ui.home.previousYearQuestionPaper

import com.welkinwits.service.respose.homeBanner.previousYearQuestionPapers.PreviousYearQuestionResponse

interface IPreviousYearQuestionPapers {
    fun clickEvent(id: PreviousYearQuestionResponse.Datum)
}