package com.witsclassdevelopment.ui.home.jobupdates

import com.witsclassdevelopment.service.respose.homeBanner.jobupdates.JobUpdatesResponse

interface IJobUpdates {
    fun clickEvent(it1: JobUpdatesResponse.Datum)
}