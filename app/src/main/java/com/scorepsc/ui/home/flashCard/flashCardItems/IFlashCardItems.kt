package com.scorepsc.ui.home.flashCard.flashCardItems

import com.scorepsc.service.respose.homeBanner.flashCard.FlashCardGroupResponse

interface IFlashCardItems {
    fun clickEvent(id: FlashCardGroupResponse.Datum)
}