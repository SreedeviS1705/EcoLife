package com.witsclassdevelopment.ui.home.accadamicStreamSwitch

import com.witsclassdevelopment.service.respose.homeBanner.acadamicStreamSwitch.acadamicLevels.GetAcadamicLevelsResponse

interface IAcademicStreamSwitch {
    fun clickEvent(item: GetAcadamicLevelsResponse.Datum)
}