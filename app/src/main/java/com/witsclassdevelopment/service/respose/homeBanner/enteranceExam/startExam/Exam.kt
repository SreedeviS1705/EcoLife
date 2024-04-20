package com.witsclassdevelopment.service.respose.homeBanner.enteranceExam.startExam

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Exam(
    @SerializedName("id")
    @Expose
    var id: String? = null,

    @SerializedName("enrollment_id")
    @Expose
    var enrollmentId: String? = null,

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