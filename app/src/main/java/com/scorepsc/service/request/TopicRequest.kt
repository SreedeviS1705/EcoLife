package com.scorepsc.service.request


import com.google.gson.annotations.SerializedName

data class TopicRequest(
    @SerializedName("stud_id")
    val studId: String,
    @SerializedName("chapter_id")
    val chapterId: String
)