package com.witsclassdevelopment.service.respose.homeBanner.previousYearQuestionPapers

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.witsclassdevelopment.service.respose.BaseResponse

class PreviousYearQuestionResponse(
    @SerializedName("data")
    val `data`: ArrayList<Datum>,
) : BaseResponse() {
    data class Datum(
        @SerializedName("id")
        @Expose
        var id: String? = null,

        @SerializedName("name")
        @Expose
        var name: String? = null,

        @SerializedName("file")
        @Expose
        var file: String? = null,

        @SerializedName("added_on")
        @Expose
        var addedOn: String? = null
    )
}