package com.scorepsc.service.respose.homeBanner.analytics

import com.scorepsc.service.respose.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AnalyticsResponse(
    @SerializedName("data")
    val `data`: Datum,
) : BaseResponse() {
    data class Datum(
        @SerializedName("total_live_classes_attended")
        @Expose
        var totalLiveClassesAttended: Int? = null,

        @SerializedName("total_video_classes_attended")
        @Expose
        var totalVideoClassesAttended: Int? = null,

        @SerializedName("exams_attended")
        @Expose
        var examsAttended: Int? = null,

        @SerializedName("exam_passed")
        @Expose
        var examPassed: Int? = null,

        @SerializedName("subscription_expiry")
        @Expose
        var subscriptionExpiry: Int? = null
    )
}