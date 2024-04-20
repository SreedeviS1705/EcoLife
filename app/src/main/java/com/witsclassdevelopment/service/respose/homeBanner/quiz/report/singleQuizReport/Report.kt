package com.witsclassdevelopment.service.respose.homeBanner.quiz.report.singleQuizReport

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.witsclassdevelopment.model.QuestionModelData

data class Report(
    @SerializedName("question")
    @Expose
    var question: String? = null,

    @SerializedName("options")
    @Expose
    var options: ArrayList<String>? = null,

    @SerializedName("answer")
    @Expose
    var answer: String? = null,

    @SerializedName("student_answer")
    @Expose
    var studentAnswer: String? = null,

    @SerializedName("marks")
    @Expose
    var marks: String? = null,

    @SerializedName("result")
    @Expose
    var result: String? = null,

    @SerializedName("")
    @Expose
    var optionsList: ArrayList<QuestionModelData>? = null,

    /**
     * Dummy id added
     */
    @Expose
    var questionId: String? = null
)