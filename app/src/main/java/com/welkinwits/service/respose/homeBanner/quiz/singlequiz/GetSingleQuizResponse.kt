package com.welkinwits.service.respose.homeBanner.quiz.singlequiz

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.welkinwits.service.respose.BaseResponse

class GetSingleQuizResponse(
    @SerializedName("data") val `data`: Datum,
) : BaseResponse() {
    data class Datum(
        @SerializedName("quiz")
        @Expose
        var quiz: Quiz? = null,

        @SerializedName("questions")
        @Expose
        var questions: ArrayList<Question>? = null
    )
}