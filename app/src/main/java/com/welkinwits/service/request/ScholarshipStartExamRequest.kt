package com.welkinwits.service.request

import com.google.gson.annotations.SerializedName

data class ScholarshipStartExamRequest(
    @SerializedName("stud_id")
    var studId: String? = null,
    @SerializedName("enrollment_id")
    val enrollment_id: Int
)