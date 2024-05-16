package com.welkinwits.service.request.liveclass.liveClass


import com.google.gson.annotations.SerializedName

data class LiveClassRequest(
    @SerializedName("stud_id")
    var studId: String,
    @SerializedName("batch_id")
    val subId: Int
)