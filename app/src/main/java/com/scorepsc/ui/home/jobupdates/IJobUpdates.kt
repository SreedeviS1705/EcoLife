package com.scorepsc.ui.home.jobupdates

import com.scorepsc.service.respose.homeBanner.jobupdates.JobUpdatesResponse

interface IJobUpdates {
    fun clickEvent(it1: JobUpdatesResponse.Datum)
}