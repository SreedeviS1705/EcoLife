package com.scorepsc.service.respose

import com.google.gson.annotations.SerializedName

class SignUpResponse(
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
