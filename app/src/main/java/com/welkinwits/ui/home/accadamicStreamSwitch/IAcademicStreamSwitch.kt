package com.welkinwits.ui.home.accadamicStreamSwitch

import com.welkinwits.service.respose.homeBanner.acadamicStreamSwitch.acadamicLevels.GetAcadamicLevelsResponse

interface IAcademicStreamSwitch {
    fun clickEvent(item: GetAcadamicLevelsResponse.Datum)
}