package com.scorepsc.service.request


import com.google.gson.annotations.SerializedName

data class OrderInitSubscriptionAllListRequest(
    @SerializedName("stud_id")
    val studId: String,
    @SerializedName("order_id")
    val orderId: Int
)