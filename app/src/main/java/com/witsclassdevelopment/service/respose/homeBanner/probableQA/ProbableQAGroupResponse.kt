package com.witsclassdevelopment.service.respose.homeBanner.probableQA

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.witsclassdevelopment.service.respose.BaseResponse

class ProbableQAGroupResponse(
    @SerializedName("data")
    val `data`: ArrayList<Datum>,
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