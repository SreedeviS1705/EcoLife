package com.welkinwits.service.request


import com.google.gson.annotations.SerializedName

data class RefferalCodeRequest(
    @SerializedName("referral_code")
    val referralCode: String
)