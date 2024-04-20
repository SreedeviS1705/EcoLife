package com.witsclassdevelopment.service.request

import com.google.gson.annotations.SerializedName

data class ScholarshipExamsRequest(
    @SerializedName("stud_id")
    val studId: String
)