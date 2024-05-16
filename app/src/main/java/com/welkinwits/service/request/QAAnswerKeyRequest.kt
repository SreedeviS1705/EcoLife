package com.welkinwits.service.request

import com.google.gson.annotations.SerializedName

data class QAAnswerKeyRequest(
    @SerializedName("stud_id")
    val studId: String,
    @SerializedName("group_id")
    val groupId: Int
)