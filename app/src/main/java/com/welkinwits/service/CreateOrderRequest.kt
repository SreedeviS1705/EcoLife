package com.welkinwits.service


import com.google.gson.annotations.SerializedName

data class CreateOrderRequest(
    @SerializedName("stud_id")
    val studId: String,
    @SerializedName("package_id")
    val packageId: Int,
    @SerializedName("enrollment_id")
    val enrollmentId: Int,
    @SerializedName("part_payment_id")
    val partPaymentId: Int
)