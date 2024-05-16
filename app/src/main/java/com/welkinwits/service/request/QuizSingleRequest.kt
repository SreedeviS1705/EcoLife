package com.welkinwits.service.request


import com.google.gson.annotations.SerializedName

data class QuizSingleRequest(
    @SerializedName("stud_id")
    val studId: String,
    @SerializedName("quiz_id")
    val quiz_id: String
)