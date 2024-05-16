package com.welkinwits.service.respose.homeBanner.exam.singleExam

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Exam(
    @SerializedName("exam_id")
    @Expose
    var examId: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("subject_name")
    @Expose
    var subjectName: String? = null,

    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = null,

    @SerializedName("added_on")
    @Expose
    var addedOn: String? = null,

    @SerializedName("attended")
    @Expose
    var attended: String? = null,

    @SerializedName("record_id")
    @Expose
    var recordId: String? = null
)