package com.welkinwits.service.respose.homeBanner

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ImageBanner(
    @SerializedName("banner")
    @Expose
    var banner: String? = null,

    @SerializedName("link")
    @Expose
    var link: String? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null
)