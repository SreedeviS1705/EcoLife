package com.witsclassdevelopment.service.request


import com.google.gson.annotations.SerializedName

data class StudentIdRequest(
    @SerializedName("stud_id")
    val studId: String
)