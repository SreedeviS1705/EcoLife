package com.witsclassdevelopment.service.request


import com.google.gson.annotations.SerializedName

data class SingleEnteranceReportRequest(
    @SerializedName("stud_id")
    var studId: String,
    @SerializedName("enrollment_id")
    val enrollmentId: Int
)