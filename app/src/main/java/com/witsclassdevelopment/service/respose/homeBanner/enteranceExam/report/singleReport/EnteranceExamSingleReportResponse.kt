package com.witsclassdevelopment.service.respose.homeBanner.enteranceExam.report.singleReport

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.witsclassdevelopment.service.respose.BaseResponse

data class EnteranceExamSingleReportResponse(
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