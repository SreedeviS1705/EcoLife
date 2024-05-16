package com.welkinwits.ui.home.scholarship

import com.welkinwits.service.respose.homeBanner.scholarship.ScholarshipExamsResponse

interface IScholarshipClickEvent {
    fun clickEvent(item: ScholarshipExamsResponse.Datum?)
    fun clickPolicy(item: ScholarshipExamsResponse.Datum?)
}