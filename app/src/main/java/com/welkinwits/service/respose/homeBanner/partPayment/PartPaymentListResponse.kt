package com.welkinwits.service.respose.homeBanner.partPayment

import com.welkinwits.service.respose.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PartPaymentListResponse(
    @SerializedName("data")
    val `data`: ArrayList<Datum>,
) : BaseResponse() {
    data class Datum(
        @SerializedName("id")
        @Expose
        var id: String? = null,

        @SerializedName("amount")
        @Expose
        var amount: String? = null,

        @SerializedName("title")
        @Expose
        var title: String? = null,

        @SerializedName("description")
        @Expose
        var description: String? = null,

        @SerializedName("term_index")
        @Expose
        var termIndex: String? = null,

        @SerializedName("part_payment_status")
        @Expose
        var partPaymentStatus: String? = null,

        @SerializedName("payment_date")
        @Expose
        var paymentDate: String? = null,
    )
}