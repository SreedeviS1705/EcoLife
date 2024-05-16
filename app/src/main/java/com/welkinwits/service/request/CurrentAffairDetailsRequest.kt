package com.welkinwits.service.request


import com.google.gson.annotations.SerializedName

data class CurrentAffairDetailsRequest(
    @SerializedName("stud_id")
    val studId: String,
    @SerializedName("post_id")
    val postId: Int
)