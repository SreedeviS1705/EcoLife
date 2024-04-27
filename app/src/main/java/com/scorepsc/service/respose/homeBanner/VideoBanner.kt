package com.scorepsc.service.respose.homeBanner

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class VideoBanner(
    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = null,

    @SerializedName("video_link")
    @Expose
    var videoLink: String? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null
)