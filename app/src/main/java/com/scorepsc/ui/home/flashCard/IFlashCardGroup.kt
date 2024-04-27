package com.scorepsc.ui.home.flashCard

import com.scorepsc.service.respose.homeBanner.flashCard.FlashCardGroupResponse

interface IFlashCardGroup {
    fun clickEvent(id: FlashCardGroupResponse.Datum)
}