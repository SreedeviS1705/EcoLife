package com.welkinwits.service.request


import com.google.gson.annotations.SerializedName

data class PaymentHistoryRequest(
    @SerializedName("stud_id")
    val studId: String
)