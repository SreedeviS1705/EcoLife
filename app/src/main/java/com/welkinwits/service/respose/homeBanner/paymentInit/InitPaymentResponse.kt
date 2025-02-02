package com.welkinwits.service.respose.homeBanner.paymentInit

import com.welkinwits.service.respose.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class InitPaymentResponse(
    @SerializedName("data")
    val `data`: Datum,
) : BaseResponse() {
    data class Datum(
        @SerializedName("bdOrderId")
        @Expose
        var bdOrderId: String? = null,

        @SerializedName("authToken")
        @Expose
        var authToken: String? = null
    )
}