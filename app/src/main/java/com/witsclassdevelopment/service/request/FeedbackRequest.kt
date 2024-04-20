package com.witsclassdevelopment.service.request


import com.google.gson.annotations.SerializedName

data class FeedbackRequest(
    @SerializedName("message")
    val message: String,
    @SerializedName("stud_id")
    val studId: String
)