package com.welkinwits.service.request

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("district")
    var district: String? = null,
    @SerializedName("state")
    var state: String? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("mobile")
    var mobile: String? = null,
    @SerializedName("guardian")
    var guardian: String? = null,
    @SerializedName("guardian_mob")
    var guardianMob: String? = null,
    @SerializedName("fcm_id")
    var fcmId: String? = null,
    @SerializedName("device_id")
    var deviceId: String? = null,
    @SerializedName("academic_level")
    var academicLevel: String? = null,
    @SerializedName("id_proof")
    var idProof: String? = null,
    @SerializedName("profile_pic")
    var profilePic: String? = null,
    @SerializedName("phone_code")
    var phoneCode: String? = null,
    @SerializedName("parent_phone_code")
    var parentPhoneCode: String? = null,
    @SerializedName("referral_code")
    var referralCode: String? = null
)