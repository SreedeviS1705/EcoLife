package com.witsclassdevelopment.service.respose.homeBanner.exam.singleExam

import com.witsclassdevelopment.model.QuestionModelData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("question_id")
    @Expose
    var questionId: String? = null,

    @SerializedName("question")
    @Expose
    var question: String? = null,

    @SerializedName("options")
    @Expose
    var options: List<String>? = null,

    @SerializedName("marks")
    @Expose
    var marks: String? = null,

    @SerializedName("")
    @Expose
    var optionsList: ArrayList<QuestionModelData>? = null,
)