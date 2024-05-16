package com.welkinwits.ui.home.jobupdates

import com.welkinwits.service.respose.homeBanner.jobupdates.JobUpdatesResponse

interface IJobUpdates {
    fun clickEvent(it1: JobUpdatesResponse.Datum)
}