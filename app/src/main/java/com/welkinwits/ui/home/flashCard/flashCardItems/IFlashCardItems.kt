package com.welkinwits.ui.home.flashCard.flashCardItems

import com.welkinwits.service.respose.homeBanner.flashCard.FlashCardGroupResponse

interface IFlashCardItems {
    fun clickEvent(id: FlashCardGroupResponse.Datum)
}