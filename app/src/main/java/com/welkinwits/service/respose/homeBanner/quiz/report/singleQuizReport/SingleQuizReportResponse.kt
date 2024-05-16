package com.welkinwits.service.respose.homeBanner.quiz.report.singleQuizReport

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.welkinwits.service.respose.BaseResponse

class SingleQuizReportResponse(
    @SerializedName("data")
    val `data`: Datum,
) : BaseResponse() {
    data class Datum(
        @SerializedName("report")
        @Expose
        var report: ArrayList<Report>? = null,

        @SerializedName("analytics")
        @Expose
        var analytics: Analytics? = null
    )
}