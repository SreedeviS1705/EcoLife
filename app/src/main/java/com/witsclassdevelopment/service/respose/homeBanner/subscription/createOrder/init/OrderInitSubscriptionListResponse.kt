package com.witsclassdevelopment.service.respose.homeBanner.subscription.createOrder.init

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.witsclassdevelopment.service.respose.BaseResponse

class OrderInitSubscriptionListResponse(
    @SerializedName("data")
    val `data`: Data
) : BaseResponse() {
    data class Data(
        @SerializedName("bdOrderId")
        @Expose
        var bdOrderId: String? = null,

        @SerializedName("authToken")
        @Expose
        var authToken: String? = null
    )
}