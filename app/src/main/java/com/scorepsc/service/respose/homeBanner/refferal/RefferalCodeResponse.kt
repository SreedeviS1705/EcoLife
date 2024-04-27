package com.scorepsc.service.respose.homeBanner.refferal

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.scorepsc.service.respose.BaseResponse


data class RefferalCodeResponse(
    @SerializedName("data")
    val `data`: HomeBannerData
) : BaseResponse() {
    data class HomeBannerData(
        @SerializedName("id")
        @Expose
        var id: String? = null,

        @SerializedName("name")
        @Expose
        var name: String? = null,

        @SerializedName("mobile")
        @Expose
        var mobile: String? = null,

        @SerializedName("id_proof")
        @Expose
        var idProof: Any? = null,

        @SerializedName("designation")
        @Expose
        var designation: Any? = null,

        @SerializedName("address")
        @Expose
        var address: Any? = null,

        @SerializedName("state")
        @Expose
        var state: Any? = null,

        @SerializedName("district")
        @Expose
        var district: Any? = null,

        @SerializedName("pincode")
        @Expose
        var pincode: Any? = null,

        @SerializedName("referral_code")
        @Expose
        var referralCode: String? = null,

        @SerializedName("added_on")
        @Expose
        var addedOn: String? = null,

        @SerializedName("deleted_on")
        @Expose
        var deletedOn: Any? = null
    )
}