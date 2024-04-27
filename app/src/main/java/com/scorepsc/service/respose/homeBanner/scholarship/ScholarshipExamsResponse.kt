package com.scorepsc.service.respose.homeBanner.scholarship

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.scorepsc.service.respose.BaseResponse

class ScholarshipExamsResponse(
    @SerializedName("data")
    val `data`: ArrayList<Datum>,
) : BaseResponse() {
    data class Datum(
        @SerializedName("name")
        @Expose
        var name: String? = null,

        @SerializedName("description")
        @Expose
        var description: String? = null,

        @SerializedName("id")
        @Expose
        var id: String? = null,

        @SerializedName("enable")
        @Expose
        var enable: String? = null
    )
}