package com.scorepsc.service.request.exams


import com.google.gson.annotations.SerializedName

data class SingleExamRequest(
    @SerializedName("stud_id")
    val studId: String,
    @SerializedName("exam_id")
    val examId: Int
)