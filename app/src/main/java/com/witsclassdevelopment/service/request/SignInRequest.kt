package com.witsclassdevelopment.service.request

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("mobile")
    var mobile: String? = null,
    @SerializedName("fcm_id")
    var fcmId: String? = null,
    @SerializedName("device_id")
    var deviceId: String? = null,
    @SerializedName("phone_code")
    var phoneCode: String? = null
)