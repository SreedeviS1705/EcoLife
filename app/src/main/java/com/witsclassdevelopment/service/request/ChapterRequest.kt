package com.witsclassdevelopment.service.request


import com.google.gson.annotations.SerializedName

data class ChapterRequest(
    @SerializedName("stud_id")
    val studId: String,
    @SerializedName("sub_id")
    val subId: String
)