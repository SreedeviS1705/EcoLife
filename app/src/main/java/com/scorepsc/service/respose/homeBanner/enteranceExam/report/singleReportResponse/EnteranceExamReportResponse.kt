package com.scorepsc.service.respose.homeBanner.enteranceExam.report.singleReportResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.scorepsc.service.respose.BaseResponse

class EnteranceExamReportResponse(
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