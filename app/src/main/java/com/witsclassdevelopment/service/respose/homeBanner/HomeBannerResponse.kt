package com.witsclassdevelopment.service.respose.homeBanner


import com.witsclassdevelopment.service.respose.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HomeBannerResponse(
    @SerializedName("data")
    val `data`: HomeBannerData
) : BaseResponse() {
    data class HomeBannerData(
        @SerializedName("video_banners")
        @Expose
        var videoBanners: List<VideoBanner>? = null,

        @SerializedName("image_banners")
        @Expose
        var imageBanners: ArrayList<ImageBanner>? = null
    )
}