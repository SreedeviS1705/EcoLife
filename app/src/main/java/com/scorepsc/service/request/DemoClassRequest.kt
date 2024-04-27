package com.scorepsc.service.request


import com.google.gson.annotations.SerializedName

data class DemoClassRequest(
    @SerializedName("stud_id")
    val studId: String,
    @SerializedName("subject_id")
    val subjectId: Int
)