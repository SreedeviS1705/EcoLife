package com.witsclassdevelopment.ui.home.flashCard

import com.witsclassdevelopment.service.respose.homeBanner.flashCard.FlashCardGroupResponse

interface IFlashCardGroup {
    fun clickEvent(id: FlashCardGroupResponse.Datum)
}