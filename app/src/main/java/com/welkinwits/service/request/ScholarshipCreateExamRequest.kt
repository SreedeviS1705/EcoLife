package com.welkinwits.service.request

import com.google.gson.annotations.SerializedName

data class ScholarshipCreateExamRequest(
    @SerializedName("stud_id")
    val studId: String,
    @SerializedName("exam_id")
    val exam_id: Int
)