package com.welkinwits.service.request


import com.google.gson.annotations.SerializedName

data class UpdateProfileRequest(
    @SerializedName("district")
    var district: String? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("guardian")
    var guardian: String? = null,
    @SerializedName("guardian_mob")
    var guardianMob: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("state")
    var state: String? = null,
    @SerializedName("stud_id")
    var studId: String? = null,
    @SerializedName("parent_phone_code")
    var parentPhoneCode: String? = null
)