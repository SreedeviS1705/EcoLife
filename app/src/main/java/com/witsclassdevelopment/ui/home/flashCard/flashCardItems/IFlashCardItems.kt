package com.witsclassdevelopment.ui.home.flashCard.flashCardItems

import com.witsclassdevelopment.service.respose.homeBanner.flashCard.FlashCardGroupResponse

interface IFlashCardItems {
    fun clickEvent(id: FlashCardGroupResponse.Datum)
}