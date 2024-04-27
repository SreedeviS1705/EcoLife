package com.scorepsc.ui.home.scholarship

import com.scorepsc.service.respose.homeBanner.scholarship.ScholarshipExamsResponse

interface IScholarshipClickEvent {
    fun clickEvent(item: ScholarshipExamsResponse.Datum?)
    fun clickPolicy(item: ScholarshipExamsResponse.Datum?)
}