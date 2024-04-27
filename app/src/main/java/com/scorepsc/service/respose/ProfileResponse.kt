package com.scorepsc.service.respose


import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("data")
    val `data`: Data,
):BaseResponse() {
    data class Data(
        @SerializedName("academic_level")
        val academicLevel: String,
        @SerializedName("district")
        val district: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("guardian")
        val guardian: String,
        @SerializedName("guardian_mob")
        val guardianMob: String,
        @SerializedName("mobile")
        val mobile: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("state")
        val state: String,
        @SerializedName("phone_code")
        var phoneCode: String? = null,
        @SerializedName("parent_phone_code")
        var parentPhoneCode: String? = null
    )
}