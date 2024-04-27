package com.scorepsc.ui.home.currentAffairs

import com.scorepsc.service.respose.homeBanner.currentAfairs.CurrentAfairsResponse

interface ICurrentAffairs {
    fun clickEvent(item: CurrentAfairsResponse.Data?)
}