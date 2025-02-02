package com.welkinwits.service.respose.homeBanner.flashCard.flashcards

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.welkinwits.service.respose.BaseResponse

class FlashCardItemsResponse(
    @SerializedName("data")
    val `data`: ArrayList<Datum>
) : BaseResponse() {
    data class Datum(
        @SerializedName("id")
        @Expose
        var id: String? = null,

        @SerializedName("card_image")
        @Expose
        var cardImage: String? = null
    )
}