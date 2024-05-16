package com.welkinwits.ui.home.currentAffairs

import com.welkinwits.service.respose.homeBanner.currentAfairs.CurrentAfairsResponse

interface ICurrentAffairs {
    fun clickEvent(item: CurrentAfairsResponse.Data?)
}