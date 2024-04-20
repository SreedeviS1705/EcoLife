package com.witsclassdevelopment.service.request


import com.google.gson.annotations.SerializedName

data class PaymentInitRequest(
    @SerializedName("stud_id")
    val studId: String,
    @SerializedName("order_id")
    val orderId: Int
)