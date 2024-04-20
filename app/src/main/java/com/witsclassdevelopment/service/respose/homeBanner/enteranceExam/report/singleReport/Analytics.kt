package com.witsclassdevelopment.service.respose.homeBanner.enteranceExam.report.singleReport

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Analytics(
    @SerializedName("total_marks_gained")
    @Expose
    var totalMarksGained: String? = null,

    @SerializedName("total_marks_loss")
    @Expose
    var totalMarksLoss: Int? = null,

    @SerializedName("result_status")
    @Expose
    var resultStatus: String? = null,

    @SerializedName("questions_attended")
    @Expose
    var questionsAttended: Int? = null,

    @SerializedName("total_exam_mark")
    @Expose
    var totalExamMark: String? = null,

    @SerializedName("pass_mark")
    @Expose
    var passMark: String? = null
)