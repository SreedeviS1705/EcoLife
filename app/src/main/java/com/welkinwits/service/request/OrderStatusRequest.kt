package com.welkinwits.service.request


import com.google.gson.annotations.SerializedName

data class OrderStatusRequest(
    @SerializedName("stud_id")
    val studId: String,
    @SerializedName("order_id")
    val orderId: Int
)