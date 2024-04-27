package com.scorepsc.service.request.liveclass.batch


import com.google.gson.annotations.SerializedName

data class BatchRequest(
    @SerializedName("stud_id")
    var studId: String,
    @SerializedName("sub_id")
    val subId: Int
)