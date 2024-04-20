package com.witsclassdevelopment.service.request.quiz

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostQuizAnswerRequest(
    @SerializedName("stud_id")
    @Expose
    var studId: String? = null,

    @SerializedName("quiz_id")
    @Expose
    var quizId: Int? = null,

    @SerializedName("submit_data")
    @Expose
    var submitData: ArrayList<SubmitDatum>? = null
)