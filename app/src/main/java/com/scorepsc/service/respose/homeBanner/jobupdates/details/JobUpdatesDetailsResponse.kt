package com.scorepsc.service.respose.homeBanner.jobupdates.details

import com.scorepsc.service.respose.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class JobUpdatesDetailsResponse(
    @SerializedName("data")
    val `data`: Data
) : BaseResponse() {
    data class Data(
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
        var details: ArrayList<String>? = null,

        @SerializedName("place")
        @Expose
        var place: String? = null,

        @SerializedName("apply_link")
        @Expose
        var applyLink: String? = null
    )
}