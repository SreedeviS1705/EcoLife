package com.scorepsc.service.request.enteranceExam

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EnteranceSubmitDatum(
    @SerializedName("question_id")
    @Expose
    var questionId: Int? = null,

    @SerializedName("option_id")
    @Expose
    var optionId: Int? = null
)