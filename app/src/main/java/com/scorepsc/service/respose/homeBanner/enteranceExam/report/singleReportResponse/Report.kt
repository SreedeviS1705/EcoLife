package com.scorepsc.service.respose.homeBanner.enteranceExam.report.singleReportResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.scorepsc.ui.home.enteranceExam.report.single.EnteranceReportQuestionModelData

data class Report(
    @SerializedName("id")
    @Expose
    var id: String? = null,

    @SerializedName("question_id")
    @Expose
    var questionId: String? = null,

    @SerializedName("question")
    @Expose
    var question: String? = null,

    @SerializedName("question_image")
    @Expose
    var questionImage: String? = null,

    @SerializedName("result")
    @Expose
    var result: String? = null,

    @SerializedName("marks")
    @Expose
    var marks: String? = null,

    @SerializedName("options")
    @Expose
    var options: List<Option>? = null,

    @SerializedName("correct_option")
    @Expose
    var correctOption: Option? = null,

    @SerializedName("user_option")
    @Expose
    var userOption: Option? = null,

    @SerializedName("")
    @Expose
    var optionsList: ArrayList<EnteranceReportQuestionModelData>? = null
)