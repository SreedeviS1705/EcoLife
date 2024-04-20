package com.witsclassdevelopment.service.respose.homeBanner.scholarship.scholarshipCreateExam.scholarshipUpdateEnrollment.scholarshipStartExam.submit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.witsclassdevelopment.service.respose.BaseResponse

data class ScholarshipExamSubmitResponse(
    @SerializedName("data")
    val `data`: Datum,
) : BaseResponse() {
    data class Datum(
        @SerializedName("result")
        @Expose
        var result: String? = null,

        @SerializedName("message")
        @Expose
        var message: String? = null,

        @SerializedName("score_text")
        @Expose
        var scoreText: String? = null
    )
}