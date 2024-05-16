package com.welkinwits.service.respose.homeBanner.latestUpdates

import com.welkinwits.service.respose.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LatestUpdateResponse(
    @SerializedName("data")
    val `data`: ArrayList<Datum>,
) : BaseResponse() {
    data class Datum(
        @SerializedName("id")
        @Expose
        var id: String? = null,

        @SerializedName("title")
        @Expose
        var title: String? = null,

        @SerializedName("type")
        @Expose
        var type: String? = null,

        @SerializedName("ref_value")
        @Expose
        var refValue: String? = null
    )
}