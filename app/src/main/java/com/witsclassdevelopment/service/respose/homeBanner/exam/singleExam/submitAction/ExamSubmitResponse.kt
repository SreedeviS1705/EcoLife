package com.witsclassdevelopment.service.respose.homeBanner.exam.singleExam.submitAction

import com.witsclassdevelopment.service.respose.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ExamSubmitResponse(
    @SerializedName("data")
    val `data`: Datum
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