package com.witsclassdevelopment.ui.home.previousYearQuestionPaper

import com.witsclassdevelopment.service.respose.homeBanner.previousYearQuestionPapers.PreviousYearQuestionResponse

interface IPreviousYearQuestionPapers {
    fun clickEvent(id: PreviousYearQuestionResponse.Datum)
}