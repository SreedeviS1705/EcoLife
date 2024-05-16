package com.welkinwits.service.respose


import com.google.gson.annotations.SerializedName

data class VerifyOTPResponse(
    @SerializedName("data")
    val data: Data,
    @SerializedName("token")
    val token: String
) : BaseResponse() {
    data class Data(
        @SerializedName("stud_id")
        val studId: String
    )
}