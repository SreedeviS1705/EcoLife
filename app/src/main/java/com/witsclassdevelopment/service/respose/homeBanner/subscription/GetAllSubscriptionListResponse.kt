package com.witsclassdevelopment.service.respose.homeBanner.subscription

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.witsclassdevelopment.service.respose.BaseResponse

data class GetAllSubscriptionListResponse(
    @SerializedName("data")
    val `data`: ArrayList<HomeBannerData>
) : BaseResponse() {
    data class HomeBannerData(
        @SerializedName("id")
        @Expose
        var id: String? = null,

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

        @SerializedName("offer_amount")
        @Expose
        var offerAmount: String? = null,

        @SerializedName("amount")
        @Expose
        var amount: String? = null,

        @SerializedName("subscription_status")
        @Expose
        var subscriptionStatus: String? = null,

        @SerializedName("days_left")
        @Expose
        var daysLeft: Int? = null,

        @SerializedName("subscription_end_date")
        @Expose
        var subscriptionEndDate: String? = null
    )
}