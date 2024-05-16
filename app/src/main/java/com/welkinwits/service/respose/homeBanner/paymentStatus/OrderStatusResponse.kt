package com.welkinwits.service.respose.homeBanner.paymentStatus

import com.welkinwits.service.respose.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OrderStatusResponse(
    @SerializedName("data")
    val `data`: Datum,
) : BaseResponse() {
    data class Datum(
        @SerializedName("order")
        @Expose
        var order: String? = null,

        @SerializedName("status")
        @Expose
        var status: String? = null
    )
}