package com.scorepsc.ui.home.demoVideo

import com.scorepsc.service.respose.homeBanner.demoVideos.DemoVideosResponse

interface IDemoVideo {
    fun clickEvent(item: DemoVideosResponse.Datum?)
}