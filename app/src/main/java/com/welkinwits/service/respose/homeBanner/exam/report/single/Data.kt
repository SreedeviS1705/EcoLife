package com.welkinwits.service.respose.homeBanner.exam.report.single

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("report")
    @Expose
    var report: List<Report>? = null,

    @SerializedName("analytics")
    @Expose
    var analytics: Analytics? = null
)