package com.scorepsc.service.respose.homeBanner.importantLink

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.scorepsc.service.respose.BaseResponse

class ImportantLinkResponse(
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

        @SerializedName("link")
        @Expose
        var link: String? = null
    )
}