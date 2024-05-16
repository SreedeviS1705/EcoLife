package com.welkinwits.service.request


import com.google.gson.annotations.SerializedName

data class VerifyOTPRequest(
    @SerializedName("otp")
    val otp: String,
    @SerializedName("otp_ref")
    val otpRef: String
)