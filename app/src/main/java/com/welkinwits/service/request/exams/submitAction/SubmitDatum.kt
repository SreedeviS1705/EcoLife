package com.welkinwits.service.request.exams.submitAction

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SubmitDatum(
    @SerializedName("question_id")
    @Expose
    var questionId: Int? = null,

    @SerializedName("answer")
    @Expose
    var answer: String? = null
)