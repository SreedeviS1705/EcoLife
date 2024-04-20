package com.witsclassdevelopment.ui.home.scholarship

import com.witsclassdevelopment.service.respose.homeBanner.scholarship.ScholarshipExamsResponse

interface IScholarshipClickEvent {
    fun clickEvent(item: ScholarshipExamsResponse.Datum?)
    fun clickPolicy(item: ScholarshipExamsResponse.Datum?)
}