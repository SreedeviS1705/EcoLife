package com.scorepsc.service.respose.homeBanner.feeStructure

import com.scorepsc.service.respose.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class FeeStructureResponse(
    @SerializedName("data")
    val `data`: ArrayList<Datum>,
) : BaseResponse() {
    data class Datum(
        @SerializedName("id")
        @Expose
        var id: String? = null,

        @SerializedName("package_name")
        @Expose
        var packageName: String? = null,

        @SerializedName("description")
        @Expose
        var description: String? = null,

        @SerializedName("tenure")
        @Expose
        var tenure: String? = null,

        @SerializedName("offer_amount")
        @Expose
        var offerAmount: String? = null,

        @SerializedName("amount")
        @Expose
        var amount: String? = null,

        @SerializedName("partpay_enabled")
        @Expose
        var partpayEnabled: String? = null,

        @SerializedName("enable_option")
        @Expose
        var enableOption: String? = null
    )
}