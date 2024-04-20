package com.witsclassdevelopment.service.respose.homeBanner.enteranceExam.startExam

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.witsclassdevelopment.model.EnteranceExamQuestionModelData

data class Question(
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
    var options: List<Option>? = null,

    @SerializedName("marks")
    @Expose
    var marks: String? = null,

    @SerializedName("negative_marks")
    @Expose
    var negativeMarks: String? = null,

    @SerializedName("")
    @Expose
    var optionsList: ArrayList<EnteranceExamQuestionModelData>? = null
)