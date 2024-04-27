package com.scorepsc.service.request


import com.google.gson.annotations.SerializedName

data class CurrentAffairByGroupRequest(
    @SerializedName("stud_id")
    val studId: String,
    @SerializedName("group_id")
    val groupId: Int
)