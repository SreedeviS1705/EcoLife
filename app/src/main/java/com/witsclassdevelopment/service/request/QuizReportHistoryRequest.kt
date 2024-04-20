package com.witsclassdevelopment.service.request


import com.google.gson.annotations.SerializedName

data class QuizReportHistoryRequest(
    @SerializedName("stud_id")
    val studId: String,
    @SerializedName("chapter_id")
    val chapterId: String
)