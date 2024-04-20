package com.witsclassdevelopment.ui.home.demoVideo

import com.witsclassdevelopment.service.respose.homeBanner.demoVideos.DemoVideosResponse

interface IDemoVideo {
    fun clickEvent(item: DemoVideosResponse.Datum?)
}