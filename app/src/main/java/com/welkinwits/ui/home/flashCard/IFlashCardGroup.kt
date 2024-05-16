package com.welkinwits.ui.home.flashCard

import com.welkinwits.service.respose.homeBanner.flashCard.FlashCardGroupResponse

interface IFlashCardGroup {
    fun clickEvent(id: FlashCardGroupResponse.Datum)
}