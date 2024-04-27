package com.scorepsc.service.request


import com.google.gson.annotations.SerializedName

data class HomeBannerRequest(
    @SerializedName("stud_id")
    val studId: String
)