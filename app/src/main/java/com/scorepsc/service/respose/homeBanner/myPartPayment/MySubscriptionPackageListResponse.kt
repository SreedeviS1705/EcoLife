package com.scorepsc.service.respose.homeBanner.myPartPayment

import com.scorepsc.service.respose.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class MySubscriptionPackageListResponse(
    @SerializedName("data")
    val `data`: ArrayList<Datum>,
) : BaseResponse() {
    data class Datum(
        @SerializedName("enrollement_id")
        @Expose
        var enrollementId: String? = null,

        @SerializedName("subscription_status")
        @Expose
        var subscriptionStatus: String? = null,

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

        @SerializedName("total_amount")
        @Expose
        var totalAmount: String? = null,

        @SerializedName("amount_paid")
        @Expose
        var amountPaid: String? = null,

        @SerializedName("partpay_enabled")
        @Expose
        var partpayEnabled: String? = null,

        @SerializedName("payment_completed")
        @Expose
        var paymentCompleted: Int? = null,
        @SerializedName("package_id")
        @Expose
        var packageId: Int? = null
    )
}