package com.welkinwits.service.respose.homeBanner.acadamicStreamSwitch.acadamicLevels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.welkinwits.service.respose.BaseResponse

class GetAcadamicLevelsResponse(
    @SerializedName("data")
    val `data`: ArrayList<Datum>,
) : BaseResponse() {
    data class Datum(
        @SerializedName("ac_id")
        @Expose
        var acId: String? = null,

        @SerializedName("levels")
        @Expose
        var levels: String? = null,

        @SerializedName("is_current")
        @Expose
        var isCurrent: Int? = null,

        var isSelected:Boolean = false
    )
}
