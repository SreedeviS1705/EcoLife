package com.scorepsc.ui.home.accadamicStreamSwitch

import com.scorepsc.service.respose.homeBanner.acadamicStreamSwitch.acadamicLevels.GetAcadamicLevelsResponse

interface IAcademicStreamSwitch {
    fun clickEvent(item: GetAcadamicLevelsResponse.Datum)
}