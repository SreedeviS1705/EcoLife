package com.welkinwits.service.request


import com.google.gson.annotations.SerializedName

data class FeeStructureRequest(
    @SerializedName("stud_id")
    val studId: String,
    @SerializedName("mode_id")
    val modeId: Int
)