package com.scorepsc.ui.home

import com.scorepsc.service.respose.homeBanner.latestUpdates.LatestUpdateResponse

interface IScrollClick {
    fun clickEvent(id: LatestUpdateResponse.Datum?)
}