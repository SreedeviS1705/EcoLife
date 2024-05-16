package com.welkinwits.ui.home

import com.welkinwits.service.respose.homeBanner.latestUpdates.LatestUpdateResponse

interface IScrollClick {
    fun clickEvent(id: LatestUpdateResponse.Datum?)
}