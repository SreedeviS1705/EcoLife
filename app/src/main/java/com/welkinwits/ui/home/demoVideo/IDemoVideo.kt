package com.welkinwits.ui.home.demoVideo

import com.welkinwits.service.respose.homeBanner.demoVideos.DemoVideosResponse

interface IDemoVideo {
    fun clickEvent(item: DemoVideosResponse.Datum?)
}