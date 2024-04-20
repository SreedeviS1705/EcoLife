package com.witsclassdevelopment.ui.home

import com.witsclassdevelopment.service.respose.homeBanner.latestUpdates.LatestUpdateResponse

interface IScrollClick {
    fun clickEvent(id: LatestUpdateResponse.Datum?)
}