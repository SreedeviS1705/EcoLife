package com.scorepsc.service.respose.homeBanner.exam.report

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.scorepsc.service.respose.BaseResponse


class ExamReportResponse(
    @SerializedName("data")
    val `data`: ArrayList<Datum>,
) : BaseResponse() {
    data class Datum(
        @SerializedName("id")
        @Expose
        var id: String? = null,

        @SerializedName("result")
        @Expose
        var result: String? = null,

        @SerializedName("total_marks")
        @Expose
        var marks: String? = null,

        @SerializedName("max_marks")
        @Expose
        var max_marks: String? = null,

        @SerializedName("attented_on")
        @Expose
        var attentedOn: String? = null,

        @SerializedName("name")
        @Expose
        var name: String? = null,

        @SerializedName("subject_name")
        @Expose
        var subjectName: String? = null
    )
}