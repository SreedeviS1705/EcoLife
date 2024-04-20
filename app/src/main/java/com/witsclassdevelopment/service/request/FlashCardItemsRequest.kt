package com.witsclassdevelopment.service.request


import com.google.gson.annotations.SerializedName

data class FlashCardItemsRequest(
    @SerializedName("stud_id")
    val studId: String,
    @SerializedName("group_id")
    val groupId: String
)