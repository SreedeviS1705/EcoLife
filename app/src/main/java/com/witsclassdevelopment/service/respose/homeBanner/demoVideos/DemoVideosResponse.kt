package com.witsclassdevelopment.service.respose.homeBanner.demoVideos

import com.witsclassdevelopment.service.respose.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class DemoVideosResponse(
    @SerializedName("data")
    val `data`: ArrayList<Datum>
) : BaseResponse() {
    data class Datum(
        @SerializedName("id")
        @Expose
        var id: String? = null,

        @SerializedName("title")
        @Expose
        var title: String? = null,

        @SerializedName("video_link")
        @Expose
        var videoLink: String? = null
    )
}