package com.witsclassdevelopment.service.respose.homeBanner.enteranceExam.answerkey

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.witsclassdevelopment.service.respose.BaseResponse

class EnteranceExamAnswerKeyResponse(
    @SerializedName("data")
    val `data`: ArrayList<Datum>,
) : BaseResponse() {
    data class Datum(
        @SerializedName("id")
        @Expose
        var id: String? = null,

        @SerializedName("question")
        @Expose
        var question: String? = null,

        @SerializedName("question_image")
        @Expose
        var questionImage: String? = null,

        @SerializedName("answer")
        @Expose
        var answer: String? = null,

        @SerializedName("explanation")
        @Expose
        var explanation: ArrayList<Explanation>? = null,

        @SerializedName("correct_option")
        @Expose
        var correctOption: Option? = null
    )
}