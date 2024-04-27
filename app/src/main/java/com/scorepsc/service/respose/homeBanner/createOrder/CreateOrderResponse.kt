package com.scorepsc.service.respose.homeBanner.createOrder

import com.scorepsc.service.respose.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class CreateOrderResponse(
    @SerializedName("data")
    val `data`: Datum
) : BaseResponse() {
    data class Datum(
        @SerializedName("id")
        @Expose
        var id: String? = null,

        @SerializedName("enrollment_id")
        @Expose
        var enrollmentId: String? = null,

        @SerializedName("package_id")
        @Expose
        var packageId: String? = null,

        @SerializedName("part_payment_id")
        @Expose
        var partPaymentId: String? = null,

        @SerializedName("full_amount")
        @Expose
        var fullAmount: String? = null,

        @SerializedName("part_amount")
        @Expose
        var partAmount: String? = null,

        @SerializedName("order_amount")
        @Expose
        var orderAmount: String? = null,

        @SerializedName("status")
        @Expose
        var status: String? = null,

        @SerializedName("added_on")
        @Expose
        var addedOn: String? = null
    )
}