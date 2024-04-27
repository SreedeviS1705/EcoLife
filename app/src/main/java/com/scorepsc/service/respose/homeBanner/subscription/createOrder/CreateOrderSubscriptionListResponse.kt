package com.scorepsc.service.respose.homeBanner.subscription.createOrder

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.scorepsc.service.respose.BaseResponse

data class CreateOrderSubscriptionListResponse(
    @SerializedName("data")
    val `data`: Data
) : BaseResponse() {
    data class Data(
        @SerializedName("order_id")
        @Expose
        var orderId: Int? = null,

        @SerializedName("package_name")
        @Expose
        var packageName: String? = null,

        @SerializedName("description")
        @Expose
        var description: String? = null,

        @SerializedName("tenure")
        @Expose
        var tenure: String? = null,

        @SerializedName("tenure_count")
        @Expose
        var tenureCount: String? = null,

        @SerializedName("amount")
        @Expose
        var amount: String? = null
    )
}