package com.welkinwits.service.respose.homeBanner.enteranceExam

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.welkinwits.service.respose.BaseResponse


class EnteranceExamQuestionTypeResponse(
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