package com.scorepsc.service.respose.homeBanner.paymentHistory

import com.scorepsc.service.respose.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetPaymentHistoryResponse(
    @SerializedName("data")
    val `data`: ArrayList<Datum>,
) : BaseResponse() {
    data class Datum(
        @SerializedName("id")
        @Expose
        var orderId: String? = null,

        @SerializedName("order_status")
        @Expose
        var orderStatus: String? = null,

        @SerializedName("order_amount")
        @Expose
        var orderAmount: String? = null,

        @SerializedName("vendor_payment_method_type")
        @Expose
        var vendorPaymentMethodType: String? = null,

        @SerializedName("vendor_trans_id")
        @Expose
        var vendorTransId: String? = null,

        @SerializedName("added_on")
        @Expose
        var addedOn: String? = null,

        @SerializedName("package_name")
        @Expose
        var packageName: String? = null
    )
}