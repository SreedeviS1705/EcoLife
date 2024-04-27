package com.scorepsc.service.respose.homeBanner.scholarship.scholarshipCreateExam.scholarshipUpdateEnrollment.scholarshipStartExam

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Exam(
    @SerializedName("id")
    @Expose
    var id: String? = null,

    @SerializedName("exam_row_id")
    @Expose
    var examRowId: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("description")
    @Expose
    var description: String? = null,

    @SerializedName("max_marks")
    @Expose
    var maxMarks: String? = null,

    @SerializedName("pass_marks")
    @Expose
    var passMarks: String? = null,

    @SerializedName("time")
    @Expose
    var time: String? = null,

    @SerializedName("started_at")
    @Expose
    var startedAt: String? = null,

    @SerializedName("ending_at")
    @Expose
    var endingAt: String? = null
)