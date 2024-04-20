package com.witsclassdevelopment.service.respose.homeBanner.exam.singleExam

import com.witsclassdevelopment.service.respose.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SingleExamResponce(
    @SerializedName("data")
    val `data`: Datum,
) : BaseResponse() {
    data class Datum(
        @SerializedName("exam")
        @Expose
        var exam: Exam? = null,

        @SerializedName("questions")
        @Expose
        var questions: List<Question>? = null
    )
}