package com.scorepsc.service.request


import com.google.gson.annotations.SerializedName

data class ChangeAcadamicStreamSwtichRequest(
    @SerializedName("stud_id")
    val studId: String,
    @SerializedName("ac_id")
    val acid: Int
)