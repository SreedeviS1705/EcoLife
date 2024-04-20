package com.witsclassdevelopment.service.respose.homeBanner.enteranceExam.report.singleReport

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.witsclassdevelopment.model.QuestionModelData

data class Report(
    @SerializedName("id")
    @Expose
    var id: String? = null,

    @SerializedName("question")
    @Expose
    var question: String? = null,

    @SerializedName("question_image")
    @Expose
    var questionImage: String? = null,

    @SerializedName("options")
    @Expose
    var options: List<String>? = null,

    @SerializedName("answer")
    @Expose
    var answer: String? = null,

    @SerializedName("correct_answer")
    @Expose
    var correctAnswer: String? = null,

    @SerializedName("result")
    @Expose
    var result: String? = null,

    @SerializedName("marks")
    @Expose
    var marks: String? = null,

    @SerializedName("explanation")
    @Expose
    var explanation: String? = null,

    @SerializedName("")
    @Expose
    var optionsList: ArrayList<QuestionModelData>? = null,

    /**
     * Dummy id added
     */
    @Expose
    var questionId: String? = null
)