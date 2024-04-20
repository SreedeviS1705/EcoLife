package com.witsclassdevelopment.service.respose.homeBanner.quiz.report

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.witsclassdevelopment.service.respose.BaseResponse

class QuizReportsResponse(
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

        @SerializedName("added_on")
        @Expose
        var addedOn: String? = null,

        @SerializedName("max_marks")
        @Expose
        var maxMarks: String? = null,

        @SerializedName("attend_on")
        @Expose
        var attendOn: String? = null,

        @SerializedName("result")
        @Expose
        var result: String? = null,

        @SerializedName("marks")
        @Expose
        var marks: String? = null
    )
}