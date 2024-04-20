package com.witsclassdevelopment.service.respose

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("token")
    val token: String
) : BaseResponse() {
    data class Data(
        @SerializedName("otp_ref")
        val otpRef: Int
    )
}