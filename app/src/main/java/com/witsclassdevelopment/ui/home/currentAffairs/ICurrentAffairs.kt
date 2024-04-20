package com.witsclassdevelopment.ui.home.currentAffairs

import com.witsclassdevelopment.service.respose.homeBanner.currentAfairs.CurrentAfairsResponse

interface ICurrentAffairs {
    fun clickEvent(item: CurrentAfairsResponse.Data?)
}