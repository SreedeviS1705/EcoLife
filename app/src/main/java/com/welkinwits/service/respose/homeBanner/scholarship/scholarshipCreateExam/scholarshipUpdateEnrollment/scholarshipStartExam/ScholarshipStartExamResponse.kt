package com.welkinwits.service.respose.homeBanner.scholarship.scholarshipCreateExam.scholarshipUpdateEnrollment.scholarshipStartExam

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.welkinwits.service.respose.BaseResponse

data class ScholarshipStartExamResponse(
    @SerializedName("data")
    val `data`: Datum,
) : BaseResponse() {
    data class Datum(
        @SerializedName("exam")
        @Expose
        var exam: Exam? = null,

        @SerializedName("questions")
        @Expose
        var questions: ArrayList<Question>? = null
    )
}