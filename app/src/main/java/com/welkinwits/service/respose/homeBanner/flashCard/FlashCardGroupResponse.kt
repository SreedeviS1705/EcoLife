package com.welkinwits.service.respose.homeBanner.flashCard

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.welkinwits.service.respose.BaseResponse

class FlashCardGroupResponse(
    @SerializedName("data")
    val `data`: ArrayList<Datum>
) : BaseResponse() {
    data class Datum(
        @SerializedName("id")
        @Expose
        var id: String? = null,

        @SerializedName("name")
        @Expose
        var name: String? = null
    )
}