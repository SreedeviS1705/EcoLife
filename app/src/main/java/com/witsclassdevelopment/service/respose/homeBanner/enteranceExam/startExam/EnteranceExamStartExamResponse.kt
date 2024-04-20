package com.witsclassdevelopment.service.respose.homeBanner.enteranceExam.startExam

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.witsclassdevelopment.service.respose.BaseResponse

data class EnteranceExamStartExamResponse(
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