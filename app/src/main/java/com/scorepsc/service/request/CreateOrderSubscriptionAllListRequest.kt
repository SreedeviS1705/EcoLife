package com.scorepsc.service.request


import com.google.gson.annotations.SerializedName

data class CreateOrderSubscriptionAllListRequest(
    @SerializedName("stud_id")
    val studId: String,
    @SerializedName("package_id")
    val packageId: Int
)