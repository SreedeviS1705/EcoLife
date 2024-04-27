package com.scorepsc.service.respose.homeBanner.enteranceExam.report

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.scorepsc.service.respose.BaseResponse

class EnteranceExamReportsResponse(
    @SerializedName("data")
    val `data`: ArrayList<Datum>,
) : BaseResponse() {
    data class Datum(
        @SerializedName("id")
        @Expose
        var id: String? = null,

        @SerializedName("exam_status")
        @Expose
        var examStatus: String? = null,

        @SerializedName("marks")
        @Expose
        var marks: String? = null,

        @SerializedName("added_on")
        @Expose
        var addedOn: String? = null,

        @SerializedName("name")
        @Expose
        var name: String? = null,

        @SerializedName("pass_marks")
        @Expose
        var passMarks: String? = null,

        @SerializedName("max_marks")
        @Expose
        var grossMarks: String? = null
    )
}