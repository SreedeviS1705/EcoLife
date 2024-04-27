package com.scorepsc.service.request


import com.google.gson.annotations.SerializedName

data class SingleExamReportRequest(
    @SerializedName("stud_id")
    var studId: String,
    @SerializedName("record_id")
    val recordId: Int
)