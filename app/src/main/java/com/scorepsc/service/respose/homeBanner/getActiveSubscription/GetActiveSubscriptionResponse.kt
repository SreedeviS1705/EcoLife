package com.scorepsc.service.respose.homeBanner.getActiveSubscription

import com.scorepsc.service.respose.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class   GetActiveSubscriptionResponse(
    @SerializedName("data")
    val `data`: Datum
) : BaseResponse() {
    data class Datum(
        @SerializedName("status")
        @Expose
        var subscriptionStatus: String? = null,

        @SerializedName("package_name")
        @Expose
        var packageName: String? = null,

        @SerializedName("end_date")
        @Expose
        var endDate: String? = null
    )
}