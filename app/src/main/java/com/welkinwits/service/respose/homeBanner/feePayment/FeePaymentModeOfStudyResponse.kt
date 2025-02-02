package com.welkinwits.service.respose.homeBanner.feePayment

import com.welkinwits.service.respose.BaseResponse
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class FeePaymentModeOfStudyResponse(
    @SerializedName("data")
    val `data`: ArrayList<Datum>,
) : BaseResponse() {
    data class Datum(
        @SerializedName("id")
        @Expose
        var id: String? = null,

        @SerializedName("name")
        @Expose
        var name: String? = null,

        @SerializedName("description")
        @Expose
        var description: String? = null
    )
}