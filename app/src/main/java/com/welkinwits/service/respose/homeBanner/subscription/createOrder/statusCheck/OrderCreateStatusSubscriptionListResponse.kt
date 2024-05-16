package com.welkinwits.service.respose.homeBanner.subscription.createOrder.statusCheck

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.welkinwits.service.respose.BaseResponse

data class OrderCreateStatusSubscriptionListResponse(
    @SerializedName("data")
    val `data`: Data
) : BaseResponse() {
    data class Data(
        @SerializedName("order")
        @Expose
        var order: String? = null,

        @SerializedName("status")
        @Expose
        var status: String? = null
    )
}