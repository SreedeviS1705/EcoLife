package com.witsclassdevelopment.service.request


import com.google.gson.annotations.SerializedName

data class ResendOTPRequest(
    @SerializedName("otp_ref")
    val otpRef: String
)