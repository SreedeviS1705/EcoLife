package com.welkinwits.service.request


import com.google.gson.annotations.SerializedName

data class WatchedListRequest(
    @SerializedName("stud_id")
    val studId: String,
    @SerializedName("topic_id")
    val topicId: String
)