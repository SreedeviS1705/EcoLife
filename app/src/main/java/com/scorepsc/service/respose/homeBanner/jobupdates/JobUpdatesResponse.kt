package com.scorepsc.service.respose.homeBanner.jobupdates

import com.scorepsc.service.respose.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class JobUpdatesResponse(
    @SerializedName("data")
    val `data`: ArrayList<Datum>
) : BaseResponse() {
    data class Datum(
        @SerializedName("id")
        @Expose
        var id: String? = null,

        @SerializedName("title")
        @Expose
        var title: String? = null,

        @SerializedName("description")
        @Expose
        var description: String? = null,

        @SerializedName("details")
        @Expose
        var details: List<String>? = null,

        @SerializedName("place")
        @Expose
        var place: String? = null,

        @SerializedName("apply_link")
        @Expose
        var applyLink: String? = null
    )
}