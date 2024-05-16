package com.welkinwits.service.respose.homeBanner.quiz.singlequiz

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.welkinwits.model.QuestionModelData

data class Question(
    @SerializedName("question_id")
    @Expose
    var questionId: String? = null,

    @SerializedName("question")
    @Expose
    var question: String? = null,

    @SerializedName("options")
    @Expose
    var options: ArrayList<String>? = null,

    @SerializedName("marks")
    @Expose
    var marks: String? = null,

    @SerializedName("")
    @Expose
    var optionsList: ArrayList<QuestionModelData>? = null,
)