package com.welkinwits.service.respose.homeBanner.quiz.singlequiz.answer

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.welkinwits.service.respose.BaseResponse

class PostQuizAnswerResponce(
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