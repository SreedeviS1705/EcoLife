package com.witsclassdevelopment.service.respose.homeBanner.demoClassSubject

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.witsclassdevelopment.service.respose.BaseResponse

data class DemoClassWithSubjectResponse(
    @SerializedName("data")
    val `data`: ArrayList<HomeBannerData>
) : BaseResponse() {
    data class HomeBannerData(
        @SerializedName("id")
        @Expose
        var id: String? = null,

        @SerializedName("image")
        @Expose
        var image: String? = null,

        @SerializedName("title")
        @Expose
        var title: String? = null,

        @SerializedName("video_link")
        @Expose
        var videoLink: String? = null
    )
}