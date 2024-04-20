package com.witsclassdevelopment.service.respose.homeBanner.scholarship.scholarshipCreateExam.scholarshipUpdateEnrollment.scholarshipStartExam

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.witsclassdevelopment.model.QuestionModelData

data class Question (
    @SerializedName("id")
    @Expose
    var id: String? = null,

    @SerializedName("question")
    @Expose
    var question: String? = null,

    @SerializedName("options")
    @Expose
    var options: List<String>? = null,

    @SerializedName("marks")
    @Expose
    var marks: String? = null,

    @SerializedName("negative_marks")
    @Expose
    var negativeMarks: String? = null,

    @SerializedName("")
    @Expose
    var optionsList: ArrayList<QuestionModelData>? = null

    )